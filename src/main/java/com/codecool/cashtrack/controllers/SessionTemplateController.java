package com.codecool.cashtrack.controllers;

import com.codecool.cashtrack.application.DTOs.incoming.SessionTemplateRequestDTO;
import com.codecool.cashtrack.controllers.utils.ResponseUtil;
import com.codecool.cashtrack.domain.entities.SessionTemplate;
import com.codecool.cashtrack.domain.services.SessionTemplateService;
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
    public ResponseEntity<SessionTemplate> createSessionTemplate(@RequestBody SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateService.createSessionTemplate(sessionTemplateRequestDTO);
        return ResponseUtil.wrapEntity(sessionTemplate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionTemplate> readSessionTemplate(@PathVariable Long id) {
        SessionTemplate sessionTemplate = sessionTemplateService.readSessionTemplate(id);
        return ResponseUtil.wrapEntity(sessionTemplate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionTemplate> updateSessionTemplate(@PathVariable Long id, @RequestBody SessionTemplateRequestDTO sessionTemplateRequestDTO) {
        SessionTemplate sessionTemplate = sessionTemplateService.updateSessionTemplate(id, sessionTemplateRequestDTO);
        return ResponseUtil.wrapEntity(sessionTemplate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSessionTemplate(@PathVariable Long id) {
        sessionTemplateService.deleteSessionTemplate(id);
        return ResponseUtil.wrapEntity(null);
    }
}
