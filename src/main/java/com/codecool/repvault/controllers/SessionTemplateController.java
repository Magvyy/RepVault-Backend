package com.codecool.repvault.controllers;

import com.codecool.repvault.application.DTOs.incoming.SessionTemplateRequestDTO;
import com.codecool.repvault.application.DTOs.outgoing.SessionTemplateResponseDTO;
import com.codecool.repvault.controllers.utils.ResponseUtil;
import com.codecool.repvault.domain.entities.SessionTemplate;
import com.codecool.repvault.domain.services.SessionTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions/templates")
public class SessionTemplateController {
    private final SessionTemplateService sessionTemplateService;

    public SessionTemplateController(SessionTemplateService sessionTemplateService) {
        this.sessionTemplateService = sessionTemplateService;
    }

    @PostMapping()
    public ResponseEntity<SessionTemplateResponseDTO> createSessionTemplate(@RequestBody SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateService.createSessionTemplate(sessionTemplateRequestDTO);
        SessionTemplateResponseDTO sessionTemplateResponseDTO = new SessionTemplateResponseDTO(sessionTemplate);
        return ResponseUtil.wrapEntity(sessionTemplateResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionTemplateResponseDTO> readSessionTemplate(@PathVariable Long id) {
        SessionTemplate sessionTemplate = sessionTemplateService.readSessionTemplate(id);
        SessionTemplateResponseDTO sessionTemplateResponseDTO = new SessionTemplateResponseDTO(sessionTemplate);
        return ResponseUtil.wrapEntity(sessionTemplateResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionTemplateResponseDTO> updateSessionTemplate(@PathVariable Long id, @RequestBody SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateService.updateSessionTemplate(id, sessionTemplateRequestDTO);
        SessionTemplateResponseDTO sessionTemplateResponseDTO = new SessionTemplateResponseDTO(sessionTemplate);
        return ResponseUtil.wrapEntity(sessionTemplateResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSessionTemplate(@PathVariable Long id) {
        sessionTemplateService.deleteSessionTemplate(id);
        return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
    }
}
