package com.codecool.repvault.application.DTOs.incoming

import com.codecool.repvault.domain.entities.enums.ExerciseEnum

data class ExerciseRequestDTO (
    val type: ExerciseEnum,
    val description: String,
    val sets: MutableList<SetRequestDTO>
)
