package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.ExerciseTemplate;
import com.codecool.repvault.domain.entities.SessionTemplate;
import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class SessionTemplateOverviewResponseDTO {
    private Long id;
    private String name;
    private List<ExerciseEnum> exercises;

    public SessionTemplateOverviewResponseDTO(SessionTemplate sessionTemplate) {
        this.id = sessionTemplate.getId();
        this.name = sessionTemplate.getName();
        this.exercises = sessionTemplate.getExercises().stream()
                .map(ExerciseTemplate::getType)
                .toList();
    }
}
