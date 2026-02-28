package com.codecool.cashtrack.application.DTOs.incoming;

import com.codecool.cashtrack.domain.entities.enums.ExerciseEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExerciseRequestDTO {
    private ExerciseEnum exerciseType;
    private String description;
    private List<SetRequestDTO> sets;
}
