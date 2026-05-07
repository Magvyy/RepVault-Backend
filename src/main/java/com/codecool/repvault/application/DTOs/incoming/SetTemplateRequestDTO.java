package com.codecool.repvault.application.DTOs.incoming;

import com.codecool.repvault.domain.entities.enums.SetEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SetTemplateRequestDTO {
    private Long id;
    private SetEnum type;
    private int reps;
    private BigDecimal weight;
}
