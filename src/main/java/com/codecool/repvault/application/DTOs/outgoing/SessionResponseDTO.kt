package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.Exercise
import com.codecool.repvault.domain.entities.Session
import java.time.ZonedDateTime

class SessionResponseDTO(session: Session) {
    val id: Long = session.id!!
    val user: UserResponseDTO = UserResponseDTO(session.user!!)
    val volume: Double? = session.volume
    val name: String = session.name!!
    val description: String? = session.description
    val exercises: MutableList<ExerciseResponseDTO> = session.exercises.stream()
        .map<ExerciseResponseDTO> { exercise: Exercise -> ExerciseResponseDTO(exercise) }
        .toList()
    val start: ZonedDateTime = session.start!!
    val end: ZonedDateTime = session.end!!
}
