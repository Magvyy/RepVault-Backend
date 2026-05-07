package com.codecool.repvault.domain.services;

import com.codecool.repvault.application.DTOs.incoming.SessionTemplateRequestDTO;
import com.codecool.repvault.domain.entities.SessionTemplate;
import com.codecool.repvault.domain.entities.User;
import com.codecool.repvault.domain.exceptions.SessionException;
import com.codecool.repvault.domain.utils.SecurityUtil;
import com.codecool.repvault.domain.utils.SessionTemplateUtil;
import com.codecool.repvault.infrastructure.repositories.SessionTemplateRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SessionTemplateService {
    private final SessionTemplateRepository sessionTemplateRepository;
    private final SessionTemplateUtil sessionTemplateUtil;
    private final SecurityUtil securityUtil;
    private final int pageSize = 10;

    public SessionTemplateService(SessionTemplateRepository sessionTemplateRepository, SessionTemplateUtil sessionTemplateUtil, SecurityUtil securityUtil) {
        this.sessionTemplateRepository = sessionTemplateRepository;
        this.sessionTemplateUtil = sessionTemplateUtil;
        this.securityUtil = securityUtil;
    }

    public SessionTemplate createSessionTemplate(SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateUtil.convertToEntity(sessionTemplateRequestDTO);
        return sessionTemplateRepository.save(sessionTemplate);
    }

    public List<SessionTemplate> getSessionTemplates(int offset) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(offset / pageSize, pageSize, sort);
        User user = securityUtil.getAuthenticatedUser();
        return sessionTemplateRepository.findByUserId(user.getId(), pageable);
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
