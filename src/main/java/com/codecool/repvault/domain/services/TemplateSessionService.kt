package com.codecool.repvault.domain.services

import com.codecool.repvault.application.DTOs.incoming.TemplateSessionRequestDTO
import com.codecool.repvault.domain.entities.ActiveSession
import com.codecool.repvault.domain.entities.TemplateSession
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.domain.utils.ActiveSessionUtil
import com.codecool.repvault.domain.utils.SecurityUtil
import com.codecool.repvault.domain.utils.TemplateSessionUtil
import com.codecool.repvault.infrastructure.repositories.ActiveSessionRepository
import com.codecool.repvault.infrastructure.repositories.TemplateSessionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TemplateSessionService(
    private val templateSessionRepository: TemplateSessionRepository,
    private val activeSessionRepository: ActiveSessionRepository,
    private val templateSessionUtil: TemplateSessionUtil,
    private val activeSessionUtil: ActiveSessionUtil,
    private val securityUtil: SecurityUtil
) {
    private val pageSize = 10

    fun createSessionTemplate(templateSessionRequestDTO: TemplateSessionRequestDTO): TemplateSession {
        val sessionTemplate = templateSessionUtil.convertToEntity(null, templateSessionRequestDTO)
        return templateSessionRepository.save<TemplateSession>(sessionTemplate)
    }

    fun startSessionTemplate(templateSessionId: Long): ActiveSession {
        val sessionTemplate = templateSessionUtil.findByIdOrThrow(templateSessionId)

        if (!templateSessionUtil.ownsSessionTemplate(sessionTemplate)) throw SessionException(
            "Doesn't own this session template",
            HttpStatus.FORBIDDEN
        )

        if (activeSessionUtil.hasActiveSession()) throw SessionException(
            "User already has an active session",
            HttpStatus.BAD_REQUEST
        )

        val session = ActiveSession(sessionTemplate)
        return activeSessionRepository.save(session)
    }

    fun getSessionTemplates(offset: Int): MutableList<TemplateSession> {
        val sort = Sort.by("id").descending()
        val pageable: Pageable = PageRequest.of(offset / pageSize, pageSize, sort)
        val user = securityUtil.authenticatedUser
        return templateSessionRepository.findByUserId(user.id!!, pageable)
    }

    fun readSessionTemplate(templateSessionId: Long): TemplateSession {
        val sessionTemplate = templateSessionUtil.findByIdOrThrow(templateSessionId)

        if (!templateSessionUtil.canViewSessionTemplate(sessionTemplate)) throw SessionException(
            "Can't view this session template",
            HttpStatus.FORBIDDEN
        )

        return sessionTemplate
    }

    fun updateSessionTemplate(templateSessionId: Long, templateSessionRequestDTO: TemplateSessionRequestDTO): TemplateSession {
        var templateSession = templateSessionUtil.findByIdOrThrow(templateSessionId)

        if (!templateSessionUtil.canUpdateSessionTemplate(templateSession)) throw SessionException(
            "Can't update this session template",
            HttpStatus.FORBIDDEN
        )

        templateSession = templateSessionUtil.updateTemplateSession(templateSessionId, templateSessionRequestDTO)
        return templateSessionRepository.save(templateSession)
    }

    fun deleteSessionTemplate(templateSessionId: Long) {
        val sessionTemplate = templateSessionUtil.findByIdOrThrow(templateSessionId)

        if (!templateSessionUtil.canDeleteSessionTemplate(sessionTemplate)) throw SessionException(
            "Can't delete this session template",
            HttpStatus.FORBIDDEN
        )

        templateSessionRepository.delete(sessionTemplate)
    }
}
