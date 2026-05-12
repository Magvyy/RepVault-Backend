package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.ActiveExercise
import com.codecool.repvault.domain.entities.enums.ExerciseEnum

class ActiveExerciseResponseDTO(exercise: ActiveExercise) {
    val id: Long = exercise.id!!
    val type: ExerciseEnum = exercise.type!!
    val sets: MutableList<SetResponseDTO> = exercise.sets.stream()
        .map<SetResponseDTO>{ SetResponseDTO(it.id!!, it.type!!, it.reps, it.weight!!) }
        .toList()
}
