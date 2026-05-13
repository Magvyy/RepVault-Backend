package com.codecool.repvault.domain.services

import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO
import com.codecool.repvault.domain.entities.Session
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.domain.utils.SecurityUtil
import com.codecool.repvault.domain.utils.SessionUtil
import com.codecool.repvault.infrastructure.repositories.SessionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class SessionService(private val sessionRepository: SessionRepository, private val sessionUtil: SessionUtil, private val securityUtil: SecurityUtil) {
    private val pageSize = 2
    fun getVisibleSessions(offset: Int): MutableList<Session> {
        return sessionRepository.findVisible(securityUtil.authenticatedUser?.id, offset, pageSize)
    }

    fun getUserSessions(userId: Long, offset: Int): MutableList<Session> {
        return sessionRepository.findByUserIdIfFriends(securityUtil.authenticatedUser?.id, userId, offset, pageSize)
    }

    fun readSession(sessionId: Long): Session {
        val session = sessionUtil.findByIdOrThrow(sessionId)

        if (!sessionUtil.canViewSession(session)) throw SessionException(
            "Can't view this session",
            HttpStatus.FORBIDDEN
        )

        return sessionUtil.findByIdOrThrow(sessionId)
    }

    fun updateSession(sessionId: Long, sessionRequestDTO: SessionRequestDTO): Session {
        var session = sessionUtil.findByIdOrThrow(sessionId)

        if (!sessionUtil.canUpdateSession(session)) throw SessionException(
            "Can't update this session",
            HttpStatus.FORBIDDEN
        )

        session = sessionUtil.updateSession(sessionId, sessionRequestDTO)
        return sessionRepository.save<Session>(session)
    }

    fun deleteSession(sessionId: Long) {
        val session = sessionUtil.findByIdOrThrow(sessionId)

        if (!sessionUtil.canDeleteSession(session)) throw SessionException(
            "Can't delete this session",
            HttpStatus.FORBIDDEN
        )

        sessionRepository.delete(session)
    }
}
