package com.codecool.repvault.application.DTOs.incoming

import com.codecool.repvault.domain.entities.enums.ExerciseEnum

data class ActiveExerciseRequestDTO (
    val id: Long?,
    val description: String?,
    val type: ExerciseEnum,
    val sets: MutableList<ActiveSetRequestDTO>
)
