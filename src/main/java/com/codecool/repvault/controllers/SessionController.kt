package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO
import com.codecool.repvault.application.DTOs.outgoing.SessionResponseDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.services.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions")
class SessionController(private val sessionService: SessionService) {
    @GetMapping
    fun getHomePage(@RequestParam offset: Int): ResponseEntity<MutableList<SessionResponseDTO>> {
        val sessions = sessionService.getVisibleSessions(offset)
        val sessionResponseDTOs = sessions.stream()
            .map { SessionResponseDTO(it) }
            .toList()
        return ResponseUtil.wrapEntity(sessionResponseDTOs)
    }

    @GetMapping("/user/{id}")
    fun getUserSessions(@PathVariable("id") userId: Long, @RequestParam offset: Int): ResponseEntity<MutableList<SessionResponseDTO>> {
        val sessions = sessionService.getUserSessions(userId, offset)
        val sessionResponseDTOs = sessions.stream()
            .map { SessionResponseDTO(it) }
            .toList()
        return ResponseUtil.wrapEntity(sessionResponseDTOs)
    }

    @GetMapping("/{id}")
    fun readSession(@PathVariable id: Long): ResponseEntity<SessionResponseDTO> {
        val session = sessionService.readSession(id)
        val sessionResponseDTO = SessionResponseDTO(session)
        return ResponseUtil.wrapEntity(sessionResponseDTO)
    }

    @PutMapping("/{id}")
    fun updateSession(
        @PathVariable id: Long,
        @RequestBody sessionRequestDTO: SessionRequestDTO
    ): ResponseEntity<SessionResponseDTO> {
        val session = sessionService.updateSession(id, sessionRequestDTO)
        val sessionResponseDTO = SessionResponseDTO(session)
        return ResponseUtil.wrapEntity(sessionResponseDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteSession(@PathVariable id: Long): ResponseEntity<*> {
        sessionService.deleteSession(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }
}
