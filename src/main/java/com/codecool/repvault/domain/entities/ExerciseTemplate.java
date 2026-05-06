package com.codecool.repvault.domain.entities;

import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "exercise_templates")
public class ExerciseTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionTemplate session;

    @Column(name = "description", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    private ExerciseEnum type;

    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SetTemplate> sets = new ArrayList<>();

    public ExerciseTemplate(SessionTemplate session, String description, ExerciseEnum type) {
        this.session = session;
        this.description = description;
        this.type = type;
    }

    public ExerciseTemplate() {

    }

    public void addSet(SetTemplate set) {
        this.sets.add(set);
    }

    public void removeSet(SetTemplate set) {
        this.sets.remove(set);
    }
}