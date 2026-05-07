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

    @Enumerated(EnumType.STRING)
    private ExerciseEnum type;

    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SetTemplate> sets = new ArrayList<>();

    public ExerciseTemplate(SessionTemplate session, Long id, ExerciseEnum type) {
        this.session = session;
        this.id = id;
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