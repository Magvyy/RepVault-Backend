package com.codecool.repvault.domain.services

import com.codecool.repvault.application.DTOs.incoming.ActiveSessionRequestDTO
import com.codecool.repvault.domain.entities.ActiveSession
import com.codecool.repvault.domain.entities.Session
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.domain.utils.ActiveSessionUtil
import com.codecool.repvault.infrastructure.repositories.ActiveSessionRepository
import com.codecool.repvault.infrastructure.repositories.SessionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ActiveSessionService(
    private val activeSessionRepository: ActiveSessionRepository,
    private val sessionRepository: SessionRepository,
    private val activeSessionUtil: ActiveSessionUtil
) {
    fun getActiveSession(): ActiveSession {
        if (!activeSessionUtil.hasActiveSession()) throw SessionException(
            "User does not have an active session",
            HttpStatus.BAD_REQUEST
        )
        return activeSessionUtil.getCurrentActiveSession()
    }

    fun endActiveSession(activeSessionId: Long, activeSessionRequestDTO: ActiveSessionRequestDTO): Session {
        val activeSession = activeSessionUtil.findByIdOrThrow(activeSessionId)

        if (!activeSessionUtil.isOwner(activeSession)) throw SessionException(
            "Can't access this active session",
            HttpStatus.FORBIDDEN
        )

        val session = activeSessionUtil.endActiveSession(activeSessionRequestDTO)
        sessionRepository.save(session)
        activeSessionRepository.delete(activeSession)
        return session
    }

    fun deleteActiveSession(activeSessionId: Long) {
        val activeSession = activeSessionUtil.findByIdOrThrow(activeSessionId)

        if (!activeSessionUtil.isOwner(activeSession)) throw SessionException(
            "Can't delete this session template",
            HttpStatus.FORBIDDEN
        )

        activeSessionRepository.delete(activeSession)
    }
}
