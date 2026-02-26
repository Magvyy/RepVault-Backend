package com.codecool.cashtrack.domain.entities;

import com.codecool.cashtrack.domain.entities.enums.ExerciseEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "expenses")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Enumerated(EnumType.STRING)
    private ExerciseEnum exerciseType;

    @OneToMany(mappedBy = "exercise")
    private List<Set> sets = new ArrayList<>();

    public Exercise(Session session, ExerciseEnum exerciseType) {
        this.session = session;
        this.exerciseType = exerciseType;
    }
}