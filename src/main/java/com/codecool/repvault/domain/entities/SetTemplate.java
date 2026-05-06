package com.codecool.repvault.domain.entities;

import com.codecool.repvault.domain.entities.enums.SetEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "set_templates")
public class SetTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseTemplate exercise;

    @Enumerated(EnumType.STRING)
    private SetEnum type;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    public SetTemplate(ExerciseTemplate exercise, SetEnum type, int reps, BigDecimal weight) {
        this.exercise = exercise;
        this.type = type;
        this.reps = reps;
        this.weight = weight;
    }

    public SetTemplate() {

    }
}