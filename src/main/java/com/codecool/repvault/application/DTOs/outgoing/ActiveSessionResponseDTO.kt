package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.ActiveExercise
import com.codecool.repvault.domain.entities.ActiveSession
import java.time.ZonedDateTime

class ActiveSessionResponseDTO(session: ActiveSession) {
    val id: Long = session.id!!
    val name: String = session.name!!
    val description: String? = session.description
    val exercises: MutableList<ActiveExerciseResponseDTO> = session.exercises.stream()
        .map<ActiveExerciseResponseDTO> { exercise: ActiveExercise -> ActiveExerciseResponseDTO(exercise) }
        .toList()
    val start: ZonedDateTime = session.start!!
}
