package com.codecool.repvault.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "session_templates")
public class SessionTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExerciseTemplate> exercises = new ArrayList<>();

    public SessionTemplate(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public SessionTemplate() {

    }

    public void addExercise(ExerciseTemplate exercise) {
        this.exercises.add(exercise);
    }

    public void removeExercise(ExerciseTemplate exercise) {
        this.exercises.remove(exercise);
    }
}