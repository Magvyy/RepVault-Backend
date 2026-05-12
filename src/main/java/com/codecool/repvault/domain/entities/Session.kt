package com.codecool.repvault.domain.entities

import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO
import jakarta.persistence.*
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity
@Table(name = "sessions")
class Session {
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

    @Column(name = "end_time", nullable = false)
    var end: ZonedDateTime? = null

    @Transient
    var volume: Double? = null

    @PostLoad
    private fun calculateVolume() {
        this.volume = 0.0
        for (exercise in this.exercises) for (set in exercise.sets) volume = volume?.plus((set.weight!! * set.reps))
    }

    @OneToMany(mappedBy = "session", cascade = [CascadeType.ALL], orphanRemoval = true)
    val exercises: MutableList<Exercise> = ArrayList<Exercise>()

    constructor(session: ActiveSession) {
        this.user = session.user
        this.name = session.name
        this.description = session.description
        this.exercises.addAll(session.exercises.map { Exercise(this, it) })
        this.start = session.start
        endSession()
    }

    constructor(user: User, id: Long?, session: SessionRequestDTO) {
        this.user = user
        this.id = id
        this.name = session.name
        this.description = session.description
        this.exercises.addAll(session.exercises.map { Exercise(this, it) })
    }

    constructor()

    fun endSession() {
        val timeZone = ZoneId.of("Europe/Oslo")
        this.end = ZonedDateTime.now(timeZone)
    }

    fun addExercise(exercise: Exercise) {
        this.exercises.add(exercise)
    }

    fun removeExercise(exercise: Exercise) {
        this.exercises.remove(exercise)
    }
}