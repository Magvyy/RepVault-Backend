package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.ActiveExerciseRequestDTO
import com.codecool.repvault.application.DTOs.incoming.ExerciseRequestDTO
import com.codecool.repvault.domain.entities.enums.ExerciseEnum
import jakarta.persistence.*

@Entity
@Table(name = "exercises")
class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    var session: Session? = null

    @Column(name = "description", nullable = true)
    var description: String? = null

    @Enumerated(EnumType.STRING)
    var type: ExerciseEnum? = null

    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sets: MutableList<Set> = ArrayList<Set>()

    constructor(session: Session, exercise: ActiveExerciseRequestDTO) {
        this.session = session
        this.description = exercise.description
        this.type = exercise.type
        this.sets.addAll(exercise.sets.map { Set(this, it) })
    }

    constructor(session: Session, exercise: ExerciseRequestDTO) {
        this.session = session
        this.description = exercise.description
        this.type = exercise.type
        this.sets.addAll(exercise.sets.map { Set(this, it) })
    }

    constructor()

    fun addSet(set: Set) {
        this.sets.add(set)
    }

    fun removeSet(set: Set) {
        this.sets.remove(set)
    }
}