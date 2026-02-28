package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.application.DTOs.incoming.ExerciseRequestDTO;
import com.codecool.cashtrack.application.DTOs.incoming.SessionRequestDTO;
import com.codecool.cashtrack.application.DTOs.incoming.SetRequestDTO;
import com.codecool.cashtrack.domain.entities.*;
import com.codecool.cashtrack.domain.exceptions.SessionException;
import com.codecool.cashtrack.infrastructure.repositories.SessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionUtil {
    private final SecurityUtil securityUtil;
    private final SessionRepository sessionRepository;

    public SessionUtil(SecurityUtil securityUtil, SessionRepository sessionRepository) {
        this.securityUtil = securityUtil;
        this.sessionRepository = sessionRepository;
    }

    public Session convertToEntity(SessionRequestDTO sessionRequestDTO) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        Session session = new Session(
                authenticatedUser,
                sessionRequestDTO.getDescription()
        );
        for (ExerciseRequestDTO exerciseRequestDTO : sessionRequestDTO.getExercises()) {
            Exercise exercise = createExercise(
                    session,
                    exerciseRequestDTO
            );
            session.addExercise(exercise);
        }
        return session;
    }

    private Exercise createExercise(Session session, ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = new Exercise(
                session,
                exerciseRequestDTO.getDescription(),
                exerciseRequestDTO.getType()
        );
        for (SetRequestDTO setRequestDTO : exerciseRequestDTO.getSets()) {
            Set set = new Set(
                    exercise,
                    setRequestDTO.getType(),
                    setRequestDTO.getReps(),
                    setRequestDTO.getWeight()
            );
            exercise.addSet(set);
        }
        return exercise;
    }

    public boolean canViewSession(Session session) {
        return ownsSession(session);
    }

    public boolean canUpdateSession(Session session) {
        return ownsSession(session);
    }

    public boolean canDeleteSession(Session session) {
        return ownsSession(session);
    }

    private boolean ownsSession(Session session) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        return authenticatedUser.getId().equals(session.getUser().getId());
    }

    public Session findByIdOrThrow(Long sessionTemplateId) {
        Optional<Session> oSession = sessionRepository.findById(sessionTemplateId);
        if (oSession.isEmpty()) throw new SessionException("User does not exist", HttpStatus.NOT_FOUND);
        return oSession.get();
    }

    public void updateSession(Session session, SessionRequestDTO sessionRequestDTO) {
        Session newSession = convertToEntity(sessionRequestDTO);
        session.setExercises(newSession.getExercises());
    }
}
