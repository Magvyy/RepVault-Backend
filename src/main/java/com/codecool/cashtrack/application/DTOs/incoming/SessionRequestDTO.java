package com.codecool.cashtrack.application.DTOs.incoming;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionRequestDTO {
    private String description;
    private List<ExerciseRequestDTO> exercises;
}
