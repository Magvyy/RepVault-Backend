package com.codecool.repvault.application.DTOs.incoming;

import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExerciseTemplateRequestDTO {
    private Long id;
    private ExerciseEnum type;
    private List<SetTemplateRequestDTO> sets;
}
