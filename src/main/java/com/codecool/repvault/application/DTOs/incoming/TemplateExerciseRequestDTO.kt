package com.codecool.repvault.application.DTOs.incoming

import com.codecool.repvault.domain.entities.enums.ExerciseEnum

data class TemplateExerciseRequestDTO (
    val id: Long?,
    val type: ExerciseEnum,
    val sets: MutableList<TemplateSetRequestDTO>
)
