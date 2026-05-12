package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.Exercise
import com.codecool.repvault.domain.entities.Set
import com.codecool.repvault.domain.entities.enums.ExerciseEnum

class ExerciseResponseDTO(exercise: Exercise) {
    val id: Long = exercise.id!!
    val type: ExerciseEnum = exercise.type!!
    val description: String? = exercise.description
    val sets: MutableList<SetResponseDTO> = exercise.sets.stream()
        .map<SetResponseDTO> { set: Set -> SetResponseDTO(set.id!!, set.type!!, set.reps, set.weight!!) }
        .toList()
}
