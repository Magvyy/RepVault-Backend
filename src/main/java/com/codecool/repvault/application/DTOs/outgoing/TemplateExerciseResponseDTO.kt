package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.TemplateExercise
import com.codecool.repvault.domain.entities.enums.ExerciseEnum

class TemplateExerciseResponseDTO(exercise: TemplateExercise) {
    val id: Long = exercise.id!!
    val type: ExerciseEnum = exercise.type!!
    val sets: MutableList<SetResponseDTO> = exercise.sets.stream()
        .map<SetResponseDTO>{ SetResponseDTO(it.id!!, it.type!!, it.reps, it.weight!!) }
        .toList()
}
