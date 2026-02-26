package com.codecool.cashtrack.domain.entities;

import com.codecool.cashtrack.application.DTOs.incoming.ExpenseRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "expenses")
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Act(ExpenseRequestDTO expenseRequestDTO, User user) {
        this.cost = expenseRequestDTO.getCost();
        this.description = expenseRequestDTO.getDescription();
        this.timestamp = expenseRequestDTO.getTimestamp();
        this.user = user;
    }
}