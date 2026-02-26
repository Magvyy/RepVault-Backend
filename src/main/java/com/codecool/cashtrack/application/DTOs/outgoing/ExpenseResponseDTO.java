package com.codecool.cashtrack.application.DTOs.outgoing;

import com.codecool.cashtrack.domain.entities.Expense;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExpenseResponseDTO {
    private Long id;
    private BigDecimal cost;
    private String description;
    private LocalDateTime timestamp;

    public ExpenseResponseDTO(Expense expense) {
        this.id = expense.getId();
        this.cost = expense.getCost();
        this.description = expense.getDescription();
        this.timestamp = expense.getTimestamp();
    }
}
