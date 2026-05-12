package com.codecool.repvault.domain.utils

import com.codecool.repvault.application.DTOs.incoming.ActiveSessionRequestDTO
import com.codecool.repvault.domain.entities.ActiveSession
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.infrastructure.repositories.ActiveSessionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ActiveSessionUtil(
    private val securityUtil: SecurityUtil,
    private val activeSessionRepository: ActiveSessionRepository
) {
    fun convertToEntity(id: Long?, activeSessionRequestDTO: ActiveSessionRequestDTO): ActiveSession {
        val authenticatedUser = securityUtil.authenticatedUser
        val activeSession = ActiveSession(
            authenticatedUser,
            id,
            activeSessionRequestDTO
        )
        return activeSession
    }

    fun isOwner(session: ActiveSession): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser
        return authenticatedUser.id == session.user!!.id
    }

    fun findByIdOrThrow(sessionTemplateId: Long): ActiveSession {
        val oSessionTemplate = activeSessionRepository.findById(sessionTemplateId)
        if (oSessionTemplate.isEmpty) throw SessionException("User does not exist", HttpStatus.NOT_FOUND)
        return oSessionTemplate.get()
    }

    fun getCurrentActiveSession(): ActiveSession {
        return activeSessionRepository.findByUserId(securityUtil.authenticatedUser.id!!)
    }

    fun hasActiveSession(): Boolean {
        return activeSessionRepository.existsByUserId(securityUtil.authenticatedUser.id!!)
    }

    fun updateActiveSession(id: Long?, activeSessionRequestDTO: ActiveSessionRequestDTO): ActiveSession {
        return convertToEntity(id, activeSessionRequestDTO)
    }
}
