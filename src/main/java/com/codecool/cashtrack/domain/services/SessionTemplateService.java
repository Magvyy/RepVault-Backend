package com.codecool.cashtrack.domain.services;

import com.codecool.cashtrack.application.DTOs.incoming.SessionTemplateRequestDTO;
import com.codecool.cashtrack.domain.entities.SessionTemplate;
import com.codecool.cashtrack.domain.exceptions.SessionException;
import com.codecool.cashtrack.domain.utils.SessionTemplateUtil;
import com.codecool.cashtrack.infrastructure.repositories.SessionTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SessionTemplateService {
    private final SessionTemplateRepository sessionTemplateRepository;
    private final SessionTemplateUtil sessionTemplateUtil;

    public SessionTemplateService(SessionTemplateRepository sessionTemplateRepository, SessionTemplateUtil sessionTemplateUtil) {
        this.sessionTemplateRepository = sessionTemplateRepository;
        this.sessionTemplateUtil = sessionTemplateUtil;
    }

    public SessionTemplate createSessionTemplate(SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateUtil.convertToEntity(sessionTemplateRequestDTO);
        return sessionTemplateRepository.save(sessionTemplate);
    }

    public SessionTemplate readSessionTemplate(Long sessionTemplateId) {
        SessionTemplate sessionTemplate = sessionTemplateUtil.findByIdOrThrow(sessionTemplateId);

        if (!sessionTemplateUtil.canViewSessionTemplate(sessionTemplate)) throw new SessionException("Can't view this session template", HttpStatus.FORBIDDEN);

        return sessionTemplateUtil.findByIdOrThrow(sessionTemplateId);
    }

    public SessionTemplate updateSessionTemplate(Long sessionTemplateId, SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateUtil.findByIdOrThrow(sessionTemplateId);

        if (!sessionTemplateUtil.canUpdateSessionTemplate(sessionTemplate)) throw new SessionException("Can't update this session template", HttpStatus.FORBIDDEN);

        sessionTemplateUtil.updateSessionTemplate(sessionTemplate, sessionTemplateRequestDTO);
        return sessionTemplateRepository.save(sessionTemplate);
    }

    public void deleteSessionTemplate(Long sessionTemplateId) {
        SessionTemplate sessionTemplate = sessionTemplateUtil.findByIdOrThrow(sessionTemplateId);

        if (!sessionTemplateUtil.canDeleteSessionTemplate(sessionTemplate)) throw new SessionException("Can't delete this session template", HttpStatus.FORBIDDEN);

        sessionTemplateRepository.delete(sessionTemplate);
    }
}
