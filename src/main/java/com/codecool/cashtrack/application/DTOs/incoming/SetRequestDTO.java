package com.codecool.cashtrack.application.DTOs.incoming;

import com.codecool.cashtrack.domain.entities.enums.SetEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SetRequestDTO {
    private SetEnum type;
    private int reps;
    private BigDecimal weight;
}
