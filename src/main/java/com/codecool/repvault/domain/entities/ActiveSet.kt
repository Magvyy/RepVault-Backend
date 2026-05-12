package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.ActiveSetRequestDTO
import com.codecool.repvault.domain.entities.enums.SetEnum
import jakarta.persistence.*

@Entity
@Table(name = "active_sets")
class ActiveSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    var exercise: ActiveExercise? = null

    @Enumerated(EnumType.STRING)
    var type: SetEnum? = null

    @Column(name = "reps", nullable = false)
    var reps = 0

    @Column(name = "weight", nullable = false)
    var weight: Double? = null

    constructor(exercise: ActiveExercise, set: TemplateSet) {
        this.exercise = exercise
        this.type = set.type
        this.reps = set.reps
        this.weight = set.weight
    }

    constructor(exercise: ActiveExercise, set: ActiveSetRequestDTO) {
        this.exercise = exercise
        this.id = set.id
        this.type = set.type
        this.reps = set.reps
        this.weight = set.weight
    }

    constructor()
}