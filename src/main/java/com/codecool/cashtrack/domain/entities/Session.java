package com.codecool.cashtrack.domain.entities;

import com.codecool.cashtrack.domain.entities.enums.ExerciseEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "expenses")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "start", nullable = false)
    private ZonedDateTime start;

    @Column(name = "end", nullable = false)
    private ZonedDateTime end;

    @OneToMany(mappedBy = "session")
    private List<Exercise> exercises = new ArrayList<>();

    public Session(User user, String description) {
        this.user = user;
        this.description = description;
        ZoneId timeZone = ZoneId.of("Europe/Oslo");
        this.start = ZonedDateTime.now(timeZone);
    }

    public void endSession() {
        ZoneId timeZone = ZoneId.of("Europe/Oslo");
        this.end = ZonedDateTime.now(timeZone);
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }
}