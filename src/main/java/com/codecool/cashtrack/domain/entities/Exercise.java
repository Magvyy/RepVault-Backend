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
@Table(name = "exercises")
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
    private ExerciseEnum type;

    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Set> sets = new ArrayList<>();

    public Exercise(Session session, String description, ExerciseEnum type) {
        this.session = session;
        this.description = description;
        this.type = type;
    }

    public Exercise() {

    }

    public void addSet(Set set) {
        this.sets.add(set);
    }

    public void removeSet(Set set) {
        this.sets.remove(set);
    }
}