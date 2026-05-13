package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.ActiveSetRequestDTO
import com.codecool.repvault.application.DTOs.incoming.SetRequestDTO
import com.codecool.repvault.domain.entities.enums.SetEnum
import jakarta.persistence.*


@Entity
@Table(name = "sets")
class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    var exercise: Exercise? = null

    @Enumerated(EnumType.STRING)
    var type: SetEnum? = null

    @Column(name = "reps", nullable = false)
    var reps = 0

    @Column(name = "weight", nullable = false)
    var weight: Double? = null

    constructor(exercise: Exercise, set: ActiveSetRequestDTO) {
        this.exercise = exercise
        this.type = set.type
        this.reps = set.reps
        this.weight = set.weight
    }

    constructor(exercise: Exercise, set: SetRequestDTO) {
        this.exercise = exercise
        this.type = set.type
        this.reps = set.reps
        this.weight = set.weight
    }

    constructor()
}