package com.codecool.cashtrack.application.DTOs.outgoing;

import com.codecool.cashtrack.domain.entities.Exercise;
import com.codecool.cashtrack.domain.entities.ExerciseTemplate;
import com.codecool.cashtrack.domain.entities.enums.ExerciseEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class ExerciseResponseDTO {
    private Long id;
    private ExerciseEnum exerciseType;
    private String description;
    private List<SetResponseDTO> sets;

    public ExerciseResponseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.exerciseType = exercise.getExerciseType();
        this.description = exercise.getDescription();
        this.sets = exercise.getSets().stream()
                .map(SetResponseDTO::new)
                .toList();
    }

    public ExerciseResponseDTO(ExerciseTemplate exerciseTemplate) {
        this.id = exerciseTemplate.getId();
        this.exerciseType = exerciseTemplate.getExerciseType();
        this.description = exerciseTemplate.getDescription();
        this.sets = exerciseTemplate.getSets().stream()
                .map(SetResponseDTO::new)
                .toList();
    }
}
