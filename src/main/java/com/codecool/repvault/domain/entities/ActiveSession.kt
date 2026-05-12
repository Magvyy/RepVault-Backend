package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.ActiveSessionRequestDTO
import jakarta.persistence.*
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity
@Table(name = "active_sessions")
class ActiveSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "description", nullable = true)
    var description: String? = null

    @Column(name = "start_time", nullable = false)
    var start: ZonedDateTime? = null

    @OneToMany(mappedBy = "session", cascade = [CascadeType.ALL], orphanRemoval = true)
    val exercises: MutableList<ActiveExercise> = ArrayList<ActiveExercise>()

    constructor(session: TemplateSession) {
        this.user = session.user
        this.name = session.name
        this.exercises.addAll(session.exercises.map { ActiveExercise(this, it) })
        startSession()
    }

    constructor(user: User, id: Long?, session: ActiveSessionRequestDTO) {
        this.user = user
        this.id = id
        this.name = session.name
        this.exercises.addAll(session.exercises.map { ActiveExercise(this, it) })
        this.start = session.start
    }

    constructor()

    fun startSession() {
        val timeZone = ZoneId.of("Europe/Oslo")
        this.start = ZonedDateTime.now(timeZone)
    }

    fun addExercise(exercise: ActiveExercise) {
        this.exercises.add(exercise)
    }

    fun removeExercise(exercise: ActiveExercise) {
        this.exercises.remove(exercise)
    }
}