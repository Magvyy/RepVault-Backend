package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.application.DTOs.incoming.ExerciseRequestDTO;
import com.codecool.cashtrack.application.DTOs.incoming.SessionTemplateRequestDTO;
import com.codecool.cashtrack.application.DTOs.incoming.SetRequestDTO;
import com.codecool.cashtrack.domain.entities.ExerciseTemplate;
import com.codecool.cashtrack.domain.entities.SessionTemplate;
import com.codecool.cashtrack.domain.entities.SetTemplate;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.exceptions.SessionException;
import com.codecool.cashtrack.infrastructure.repositories.SessionTemplateRepository;
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
        for (ExerciseRequestDTO exerciseRequestDTO : sessionTemplateRequestDTO.getExercises()) {
            ExerciseTemplate exerciseTemplate = createExerciseTemplate(
                    sessionTemplate,
                    exerciseRequestDTO
            );
            sessionTemplate.addExercise(exerciseTemplate);
        }
        return sessionTemplate;
    }

    private ExerciseTemplate createExerciseTemplate(SessionTemplate sessionTemplate, ExerciseRequestDTO exerciseRequestDTO) {
        ExerciseTemplate exerciseTemplate = new ExerciseTemplate(
                sessionTemplate,
                exerciseRequestDTO.getDescription(),
                exerciseRequestDTO.getExerciseType()
        );
        for (SetRequestDTO setRequestDTO : exerciseRequestDTO.getSets()) {
            SetTemplate setTemplate = new SetTemplate(
                    exerciseTemplate,
                    setRequestDTO.getSetType(),
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
        sessionTemplate.setExercises(newSessionTemplate.getExercises());
    }
}
