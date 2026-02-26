package com.codecool.cashtrack.application.DTOs.incoming;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExpenseRequestDTO {
    private BigDecimal cost;
    private String description;
    private LocalDateTime timestamp;
}
