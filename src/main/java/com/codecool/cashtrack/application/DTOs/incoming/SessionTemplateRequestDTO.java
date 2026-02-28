package com.codecool.cashtrack.application.DTOs.incoming;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionTemplateRequestDTO {
    private String name;
    private List<ExerciseRequestDTO> exercises;
}
