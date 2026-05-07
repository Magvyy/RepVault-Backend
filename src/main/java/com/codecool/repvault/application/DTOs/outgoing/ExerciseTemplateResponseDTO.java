package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.Exercise;
import com.codecool.repvault.domain.entities.ExerciseTemplate;
import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class ExerciseTemplateResponseDTO {
    private Long id;
    private ExerciseEnum type;
    private List<SetResponseDTO> sets;

    public ExerciseTemplateResponseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.type = exercise.getType();
        this.sets = exercise.getSets().stream()
                .map(SetResponseDTO::new)
                .toList();
    }

    public ExerciseTemplateResponseDTO(ExerciseTemplate exerciseTemplate) {
        this.id = exerciseTemplate.getId();
        this.type = exerciseTemplate.getType();
        this.sets = exerciseTemplate.getSets().stream()
                .map(SetResponseDTO::new)
                .toList();
    }
}
