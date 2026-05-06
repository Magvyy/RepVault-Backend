package com.codecool.repvault.domain.services;

import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO;
import com.codecool.repvault.domain.entities.Session;
import com.codecool.repvault.domain.exceptions.SessionException;
import com.codecool.repvault.domain.utils.SessionUtil;
import com.codecool.repvault.infrastructure.repositories.SessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionUtil sessionUtil;

    public SessionService(SessionRepository sessionRepository, SessionUtil sessionUtil) {
        this.sessionRepository = sessionRepository;
        this.sessionUtil = sessionUtil;
    }

    public Session startSession(SessionRequestDTO sessionRequestDTO) {
        Session session = sessionUtil.convertToEntity(sessionRequestDTO);
        session.startSession();
        return sessionRepository.save(session);
    }

    public Session endSession(Long sessionId) {
        Session session = sessionUtil.findByIdOrThrow(sessionId);
        session.endSession();
        return sessionRepository.save(session);
    }

    public Session readSession(Long sessionId) {
        Session session = sessionUtil.findByIdOrThrow(sessionId);

        if (!sessionUtil.canViewSession(session)) throw new SessionException("Can't view this session", HttpStatus.FORBIDDEN);

        return sessionUtil.findByIdOrThrow(sessionId);
    }

    public Session updateSession(Long sessionId, SessionRequestDTO sessionRequestDTO) {
        Session session = sessionUtil.findByIdOrThrow(sessionId);

        if (!sessionUtil.canUpdateSession(session)) throw new SessionException("Can't update this session", HttpStatus.FORBIDDEN);

        sessionUtil.updateSession(session, sessionRequestDTO);
        return sessionRepository.save(session);
    }

    public void deleteSession(Long sessionId) {
        Session session = sessionUtil.findByIdOrThrow(sessionId);

        if (!sessionUtil.canDeleteSession(session)) throw new SessionException("Can't delete this session", HttpStatus.FORBIDDEN);

        sessionRepository.delete(session);
    }
}
