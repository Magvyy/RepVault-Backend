package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.incoming.ActiveSessionRequestDTO
import com.codecool.repvault.application.DTOs.outgoing.ActiveSessionResponseDTO
import com.codecool.repvault.application.DTOs.outgoing.SessionResponseDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.services.ActiveSessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions/active")
class ActiveSessionController(private val activeSessionService: ActiveSessionService) {
    @GetMapping
    fun getActiveSession(): ResponseEntity<ActiveSessionResponseDTO> {
        val session = activeSessionService.getActiveSession()
        return ResponseUtil.wrapEntity<ActiveSessionResponseDTO>(ActiveSessionResponseDTO(session))
    }

    @PutMapping("/{id}")
    fun endActiveSession(
        @PathVariable id: Long,
        @RequestBody activeSessionRequestDTO: ActiveSessionRequestDTO
    ): ResponseEntity<SessionResponseDTO> {
        val session = activeSessionService.endActiveSession(id, activeSessionRequestDTO)
        val sessionRequestDTO = SessionResponseDTO(session)
        return ResponseUtil.wrapEntity<SessionResponseDTO>(sessionRequestDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteActiveSession(@PathVariable id: Long): ResponseEntity<*> {
        activeSessionService.deleteActiveSession(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }
}
