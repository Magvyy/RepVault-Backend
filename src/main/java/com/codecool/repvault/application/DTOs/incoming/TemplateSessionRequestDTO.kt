package com.codecool.repvault.application.DTOs.incoming

data class TemplateSessionRequestDTO (
    val name: String,
    val exercises: MutableList<TemplateExerciseRequestDTO>
)
