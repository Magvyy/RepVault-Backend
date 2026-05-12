package com.codecool.repvault.application.DTOs.incoming

data class SessionRequestDTO (
    val id: Long?,
    val name: String,
    val description: String,
    val exercises: MutableList<ExerciseRequestDTO>
)
