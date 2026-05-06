package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import lombok.Getter;

@Getter
public class ExerciseTypeDTO {
    private Long id;
    private ExerciseEnum type;

    public ExerciseTypeDTO(Long id, ExerciseEnum type) {
        this.id = id;
        this.type = type;
    }
}