package com.codecool.repvault.domain.utils

import com.codecool.repvault.application.DTOs.incoming.TemplateSessionRequestDTO
import com.codecool.repvault.domain.entities.TemplateSession
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.infrastructure.repositories.TemplateSessionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class TemplateSessionUtil(
    private val securityUtil: SecurityUtil,
    private val templateSessionRepository: TemplateSessionRepository,
) {
    fun convertToEntity(id: Long?, templateSessionRequestDTO: TemplateSessionRequestDTO): TemplateSession {
        val authenticatedUser = securityUtil.authenticatedUser!!
        val templateSession = TemplateSession(
            authenticatedUser,
            id,
            templateSessionRequestDTO
        )
        return templateSession
    }

    fun canViewSessionTemplate(templateSession: TemplateSession): Boolean {
        return ownsSessionTemplate(templateSession)
    }

    fun canUpdateSessionTemplate(templateSession: TemplateSession): Boolean {
        return ownsSessionTemplate(templateSession)
    }

    fun canDeleteSessionTemplate(templateSession: TemplateSession): Boolean {
        return ownsSessionTemplate(templateSession)
    }

    fun ownsSessionTemplate(templateSession: TemplateSession): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser!!
        return authenticatedUser.id == templateSession.user!!.id
    }

    fun findByIdOrThrow(sessionTemplateId: Long): TemplateSession {
        val oSessionTemplate = templateSessionRepository.findById(sessionTemplateId)
        if (oSessionTemplate.isEmpty) throw SessionException("User does not exist", HttpStatus.NOT_FOUND)
        return oSessionTemplate.get()
    }

    fun updateTemplateSession(id: Long?, templateSessionRequestDTO: TemplateSessionRequestDTO): TemplateSession {
        return convertToEntity(id, templateSessionRequestDTO)
    }
}
