package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.SessionTemplate;
import lombok.Getter;

import java.util.List;

@Getter
public class SessionTemplateResponseDTO {
    private Long id;
    private String name;
    private List<ExerciseResponseDTO> exercises;

    public SessionTemplateResponseDTO(SessionTemplate sessionTemplate) {
        this.id = sessionTemplate.getId();
        this.name = sessionTemplate.getName();
        this.exercises = sessionTemplate.getExercises().stream()
                .map(ExerciseResponseDTO::new)
                .toList();
    }
}
