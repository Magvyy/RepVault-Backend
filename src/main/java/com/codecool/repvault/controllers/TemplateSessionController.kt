package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.incoming.TemplateSessionRequestDTO
import com.codecool.repvault.application.DTOs.outgoing.ActiveSessionResponseDTO
import com.codecool.repvault.application.DTOs.outgoing.SessionOverviewResponseDTO
import com.codecool.repvault.application.DTOs.outgoing.TemplateSessionResponseDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.entities.TemplateSession
import com.codecool.repvault.domain.services.TemplateSessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions/templates")
class TemplateSessionController(private val templateSessionService: TemplateSessionService) {
    @PostMapping
    fun createSessionTemplate(@RequestBody templateSessionRequestDTO: TemplateSessionRequestDTO): ResponseEntity<TemplateSessionResponseDTO> {
        val sessionTemplate = templateSessionService.createSessionTemplate(templateSessionRequestDTO)
        val templateSessionResponseDTO = TemplateSessionResponseDTO(sessionTemplate)
        return ResponseUtil.wrapEntity<TemplateSessionResponseDTO>(templateSessionResponseDTO)
    }

    @GetMapping("/{id}/start")
    fun startSessionTemplate(@PathVariable id: Long): ResponseEntity<ActiveSessionResponseDTO> {
        val session = templateSessionService.startSessionTemplate(id)
        return ResponseUtil.wrapEntity<ActiveSessionResponseDTO>(ActiveSessionResponseDTO(session))
    }

    @GetMapping
    fun getSessionTemplates(@RequestParam("offset") offset: Int): ResponseEntity<MutableList<SessionOverviewResponseDTO>> {
        val sessionTemplates = templateSessionService.getSessionTemplates(offset)
        val sessionOverviewResponseDTOs = sessionTemplates.stream().map<SessionOverviewResponseDTO> { templateSession: TemplateSession -> SessionOverviewResponseDTO(templateSession)  }.toList().toMutableList()
        return ResponseUtil.wrapEntity<MutableList<SessionOverviewResponseDTO>>(sessionOverviewResponseDTOs)
    }

    @GetMapping("/{id}")
    fun readSessionTemplate(@PathVariable id: Long): ResponseEntity<TemplateSessionResponseDTO> {
        val sessionTemplate = templateSessionService.readSessionTemplate(id)
        val templateSessionResponseDTO = TemplateSessionResponseDTO(sessionTemplate)
        return ResponseUtil.wrapEntity<TemplateSessionResponseDTO>(templateSessionResponseDTO)
    }

    @PutMapping("/{id}")
    fun updateSessionTemplate(
        @PathVariable id: Long,
        @RequestBody templateSessionRequestDTO: TemplateSessionRequestDTO
    ): ResponseEntity<TemplateSessionResponseDTO> {
        val sessionTemplate = templateSessionService.updateSessionTemplate(id, templateSessionRequestDTO)
        val templateSessionResponseDTO = TemplateSessionResponseDTO(sessionTemplate)
        return ResponseUtil.wrapEntity<TemplateSessionResponseDTO>(templateSessionResponseDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteSessionTemplate(@PathVariable id: Long): ResponseEntity<*> {
        templateSessionService.deleteSessionTemplate(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }
}
