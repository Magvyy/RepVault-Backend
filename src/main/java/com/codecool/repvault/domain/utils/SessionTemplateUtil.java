package com.codecool.repvault.domain.utils;

import com.codecool.repvault.application.DTOs.incoming.*;
import com.codecool.repvault.domain.entities.*;
import com.codecool.repvault.domain.exceptions.SessionException;
import com.codecool.repvault.infrastructure.repositories.SessionTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionTemplateUtil {
    private final SecurityUtil securityUtil;
    private final SessionTemplateRepository sessionTemplateRepository;

    public SessionTemplateUtil(SecurityUtil securityUtil, SessionTemplateRepository sessionTemplateRepository) {
        this.securityUtil = securityUtil;
        this.sessionTemplateRepository = sessionTemplateRepository;
    }

    public SessionTemplate convertToEntity(SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        SessionTemplate sessionTemplate = new SessionTemplate(
                authenticatedUser,
                sessionTemplateRequestDTO.getName()
        );
        for (ExerciseTemplateRequestDTO exerciseTemplateRequestDTO : sessionTemplateRequestDTO.getExercises()) {
            ExerciseTemplate exerciseTemplate = createExerciseTemplate(
                    sessionTemplate,
                    exerciseTemplateRequestDTO
            );
            sessionTemplate.addExercise(exerciseTemplate);
        }
        return sessionTemplate;
    }

    private ExerciseTemplate createExerciseTemplate(SessionTemplate sessionTemplate, ExerciseTemplateRequestDTO exerciseTemplateRequestDTO) {
        ExerciseTemplate exerciseTemplate = new ExerciseTemplate(
                sessionTemplate,
                exerciseTemplateRequestDTO.getId(),
                exerciseTemplateRequestDTO.getType()
        );
        for (SetTemplateRequestDTO setRequestDTO : exerciseTemplateRequestDTO.getSets()) {
            SetTemplate setTemplate = new SetTemplate(
                    exerciseTemplate,
                    setRequestDTO.getId(),
                    setRequestDTO.getType(),
                    setRequestDTO.getReps(),
                    setRequestDTO.getWeight()
            );
            exerciseTemplate.addSet(setTemplate);
        }
        return exerciseTemplate;
    }

    public boolean canViewSessionTemplate(SessionTemplate sessionTemplate) {
        return ownsSessionTemplate(sessionTemplate);
    }

    public boolean canUpdateSessionTemplate(SessionTemplate sessionTemplate) {
        return ownsSessionTemplate(sessionTemplate);
    }

    public boolean canDeleteSessionTemplate(SessionTemplate sessionTemplate) {
        return ownsSessionTemplate(sessionTemplate);
    }

    private boolean ownsSessionTemplate(SessionTemplate sessionTemplate) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        return authenticatedUser.getId().equals(sessionTemplate.getUser().getId());
    }

    public SessionTemplate findByIdOrThrow(Long sessionTemplateId) {
        Optional<SessionTemplate> oSessionTemplate = sessionTemplateRepository.findById(sessionTemplateId);
        if (oSessionTemplate.isEmpty()) throw new SessionException("User does not exist", HttpStatus.NOT_FOUND);
        return oSessionTemplate.get();
    }

    public void updateSessionTemplate(SessionTemplate sessionTemplate, SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate newSessionTemplate = convertToEntity(sessionTemplateRequestDTO);
        sessionTemplate.getExercises().clear();
        sessionTemplate.getExercises().addAll(newSessionTemplate.getExercises());
        for (ExerciseTemplate exerciseTemplate : sessionTemplate.getExercises()) {
            exerciseTemplate.setSession(sessionTemplate);
        }
    }
}
