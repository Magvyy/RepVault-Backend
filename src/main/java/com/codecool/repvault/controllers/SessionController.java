package com.codecool.repvault.controllers;

import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO;
import com.codecool.repvault.application.DTOs.outgoing.SessionResponseDTO;
import com.codecool.repvault.controllers.utils.ResponseUtil;
import com.codecool.repvault.domain.entities.Session;
import com.codecool.repvault.domain.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping()
    public ResponseEntity<SessionResponseDTO> startSession(@RequestBody SessionRequestDTO sessionRequestDTO) {
        Session session = sessionService.startSession(sessionRequestDTO);
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO(session);
        return ResponseUtil.wrapEntity(sessionResponseDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> endSession(@PathVariable Long id) {
        Session session = sessionService.endSession(id);
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO(session);
        return ResponseUtil.wrapEntity(sessionResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> readSession(@PathVariable Long id) {
        Session session = sessionService.readSession(id);
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO(session);
        return ResponseUtil.wrapEntity(sessionResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> updateSession(@PathVariable Long id, @RequestBody SessionRequestDTO sessionRequestDTO) {
        Session session = sessionService.updateSession(id, sessionRequestDTO);
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO(session);
        return ResponseUtil.wrapEntity(sessionResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
    }
}
