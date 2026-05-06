package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.Exercise;
import com.codecool.repvault.domain.entities.ExerciseTemplate;
import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class ExerciseResponseDTO {
    private Long id;
    private ExerciseEnum type;
    private String description;
    private List<SetResponseDTO> sets;

    public ExerciseResponseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.type = exercise.getType();
        this.description = exercise.getDescription();
        this.sets = exercise.getSets().stream()
                .map(SetResponseDTO::new)
                .toList();
    }

    public ExerciseResponseDTO(ExerciseTemplate exerciseTemplate) {
        this.id = exerciseTemplate.getId();
        this.type = exerciseTemplate.getType();
        this.description = exerciseTemplate.getDescription();
        this.sets = exerciseTemplate.getSets().stream()
                .map(SetResponseDTO::new)
                .toList();
    }
}
