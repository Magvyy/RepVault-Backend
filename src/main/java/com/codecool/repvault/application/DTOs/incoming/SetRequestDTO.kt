package com.codecool.repvault.application.DTOs.incoming

import com.codecool.repvault.domain.entities.enums.SetEnum


data class SetRequestDTO (
    val type: SetEnum,
    val reps: Int,
    val weight: Double
)
