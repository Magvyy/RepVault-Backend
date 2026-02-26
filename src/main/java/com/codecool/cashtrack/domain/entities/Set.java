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

    @Enumerated(EnumType.STRING)
    private SetEnum setType;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    public Set(SetEnum setType, int reps, BigDecimal weight, Exercise exercise) {
        this.setType = setType;
        this.reps = reps;
        this.weight = weight;
        this.exercise = exercise;
    }

    public Set(int reps, BigDecimal weight, Exercise exercise) {
        this.setType = SetEnum.NORMAL;
        this.reps = reps;
        this.weight = weight;
        this.exercise = exercise;
    }
}