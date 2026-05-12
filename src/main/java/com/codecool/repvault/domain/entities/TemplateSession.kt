package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.TemplateSessionRequestDTO
import jakarta.persistence.*

@Entity
@Table(name = "template_sessions")
class TemplateSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @Column(name = "name")
    var name: String? = null

    @OneToMany(mappedBy = "session", cascade = [CascadeType.ALL], orphanRemoval = true)
    val exercises: MutableList<TemplateExercise> = ArrayList<TemplateExercise>()

    constructor(user: User, id: Long?, session: TemplateSessionRequestDTO) {
        this.user = user
        this.id = id
        this.name = session.name
        this.exercises.addAll(session.exercises.map { TemplateExercise(this, it) })
    }

    constructor()

    fun addExercise(exercise: TemplateExercise) {
        this.exercises.add(exercise)
    }

    fun removeExercise(exercise: TemplateExercise) {
        this.exercises.remove(exercise)
    }
}