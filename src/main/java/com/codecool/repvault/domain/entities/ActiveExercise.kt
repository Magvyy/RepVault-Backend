package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.ActiveExerciseRequestDTO
import com.codecool.repvault.domain.entities.enums.ExerciseEnum
import jakarta.persistence.*

@Entity
@Table(name = "active_exercises")
class ActiveExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    var session: ActiveSession? = null

    @Column(name = "description", nullable = true)
    var description: String? = null

    @Enumerated(EnumType.STRING)
    var type: ExerciseEnum? = null

    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sets: MutableList<ActiveSet> = ArrayList<ActiveSet>()

    constructor(session: ActiveSession, exercise: TemplateExercise) {
        this.session = session
        this.type = exercise.type
        this.sets.addAll(exercise.sets.map { ActiveSet(this, it) })
    }

    constructor(session: ActiveSession, exercise: ActiveExerciseRequestDTO) {
        this.id = exercise.id
        this.session = session
        this.type = exercise.type
        this.sets.addAll(exercise.sets.map { ActiveSet(this, it) })
    }

    constructor()

    fun addSet(set: ActiveSet) {
        this.sets.add(set)
    }

    fun removeSet(set: ActiveSet) {
        this.sets.remove(set)
    }
}