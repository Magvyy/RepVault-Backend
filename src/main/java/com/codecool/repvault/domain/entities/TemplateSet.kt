package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.TemplateSetRequestDTO
import com.codecool.repvault.domain.entities.enums.SetEnum
import jakarta.persistence.*

@Entity
@Table(name = "template_sets")
class TemplateSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    var exercise: TemplateExercise? = null

    @Enumerated(EnumType.STRING)
    var type: SetEnum? = null

    @Column(name = "reps", nullable = false)
    var reps = 0

    @Column(name = "weight", nullable = false)
    var weight: Double? = null

    constructor(exercise: TemplateExercise, set: TemplateSetRequestDTO) {
        this.exercise = exercise
        this.id = set.id
        this.type = set.type
        this.reps = set.reps
        this.weight = set.weight
    }

    constructor()
}