package com.codecool.repvault.domain.entities;

import com.codecool.repvault.domain.entities.enums.SetEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Enumerated(EnumType.STRING)
    private SetEnum type;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    public Set(Exercise exercise, SetEnum type, int reps, BigDecimal weight) {
        this.exercise = exercise;
        this.type = type;
        this.reps = reps;
        this.weight = weight;
    }

    public Set(Exercise exercise, int reps, BigDecimal weight) {
        this.exercise = exercise;
        this.type = SetEnum.NORMAL;
        this.reps = reps;
        this.weight = weight;
    }

    public Set() {

    }
}