package com.codecool.repvault.domain.utils

import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO
import com.codecool.repvault.domain.entities.Session
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.infrastructure.repositories.SessionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class SessionUtil(private val securityUtil: SecurityUtil, private val sessionRepository: SessionRepository, private val friendUtil: FriendUtil) {
    fun convertToEntity(id: Long?, sessionRequestDTO: SessionRequestDTO): Session {
        val authenticatedUser = securityUtil.authenticatedUser!!
        val session = Session(
            authenticatedUser,
            id,
            sessionRequestDTO
        )
        return session
    }

    fun canViewSession(session: Session): Boolean {
        return if (session.public!!) true else (friendUtil.isFriendsWith(session.user!!))
    }

    fun canUpdateSession(session: Session): Boolean {
        return ownsSession(session)
    }

    fun canDeleteSession(session: Session): Boolean {
        return ownsSession(session)
    }

    private fun ownsSession(session: Session): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser!!
        return authenticatedUser.id == session.user!!.id
    }

    fun findByIdOrThrow(sessionTemplateId: Long): Session {
        val oSession = sessionRepository.findById(sessionTemplateId)
        if (oSession.isEmpty) throw SessionException("User does not exist", HttpStatus.NOT_FOUND)
        return oSession.get()
    }

    fun updateSession(id: Long?, sessionRequestDTO: SessionRequestDTO): Session {
        return convertToEntity(id, sessionRequestDTO)
    }
}
