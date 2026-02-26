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

    @Column(name = "description", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    private ExerciseEnum exerciseType;

    @OneToMany(mappedBy = "exercise")
    private List<Set> sets = new ArrayList<>();

    public Exercise(Session session, String description, ExerciseEnum exerciseType) {
        this.session = session;
        this.description = description;
        this.exerciseType = exerciseType;
    }
}