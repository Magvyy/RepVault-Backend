package com.codecool.cashtrack.application.DTOs.outgoing;

import com.codecool.cashtrack.domain.entities.enums.ExerciseEnum;
import lombok.Getter;

@Getter
public class ExerciseTypeResponseDTO {
    private Long id;
    private ExerciseEnum type;

    public ExerciseTypeResponseDTO(Long id, ExerciseEnum type) {
        this.id = id;
        this.type = type;
    }
}
