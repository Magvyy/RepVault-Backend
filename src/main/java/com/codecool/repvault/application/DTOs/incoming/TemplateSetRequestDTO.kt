package com.codecool.repvault.application.DTOs.incoming

import com.codecool.repvault.domain.entities.enums.SetEnum

data class TemplateSetRequestDTO (
    val id: Long?,
    val type: SetEnum,
    val reps: Int,
    val weight: Double
)