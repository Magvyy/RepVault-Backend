package com.codecool.cashtrack.controllers;

import com.codecool.cashtrack.application.DTOs.incoming.SessionRequestDTO;
import com.codecool.cashtrack.controllers.utils.ResponseUtil;
import com.codecool.cashtrack.domain.entities.Session;
import com.codecool.cashtrack.domain.services.SessionService;
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
    public ResponseEntity<Session> startSession(@RequestBody SessionRequestDTO sessionRequestDTO) {
        Session session = sessionService.startSession(sessionRequestDTO);
        return ResponseUtil.wrapEntity(session);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Session> endSession(@PathVariable Long id) {
        Session session = sessionService.endSession(id);
        return ResponseUtil.wrapEntity(session);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> readSession(@PathVariable Long id) {
        Session session = sessionService.readSession(id);
        return ResponseUtil.wrapEntity(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody SessionRequestDTO sessionRequestDTO) {
        Session session = sessionService.updateSession(id, sessionRequestDTO);
        return ResponseUtil.wrapEntity(session);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseUtil.wrapEntity(null);
    }
}
