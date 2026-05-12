package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.TemplateExerciseRequestDTO
import com.codecool.repvault.domain.entities.enums.ExerciseEnum
import jakarta.persistence.*

@Entity
@Table(name = "template_exercises")
class TemplateExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    var session: TemplateSession? = null

    @Enumerated(EnumType.STRING)
    var type: ExerciseEnum? = null

    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sets: MutableList<TemplateSet> = ArrayList<TemplateSet>()

    constructor(session: TemplateSession, exercise: TemplateExerciseRequestDTO) {
        this.session = session
        this.id = exercise.id
        this.type = exercise.type
        this.sets.addAll(exercise.sets.map { TemplateSet(this, it) })
    }

    constructor()

    fun addSet(set: TemplateSet) {
        this.sets.add(set)
    }

    fun removeSet(set: TemplateSet) {
        this.sets.remove(set)
    }
}