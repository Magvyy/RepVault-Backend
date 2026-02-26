package com.codecool.cashtrack.domain.entities;

import com.codecool.cashtrack.domain.entities.enums.SetEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "expenses")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Enumerated(EnumType.STRING)
    private SetEnum setType;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    public Set(Exercise exercise, SetEnum setType, int reps, BigDecimal weight) {
        this.exercise = exercise;
        this.setType = setType;
        this.reps = reps;
        this.weight = weight;
    }

    public Set(Exercise exercise, int reps, BigDecimal weight) {
        this.exercise = exercise;
        this.setType = SetEnum.NORMAL;
        this.reps = reps;
        this.weight = weight;
    }
}