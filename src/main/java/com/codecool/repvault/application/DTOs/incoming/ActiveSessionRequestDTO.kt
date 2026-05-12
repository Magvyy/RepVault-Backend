package com.codecool.repvault.application.DTOs.incoming

import java.time.ZonedDateTime

data class ActiveSessionRequestDTO (
    val name: String,
    val exercises: MutableList<ActiveExerciseRequestDTO>,
    val start: ZonedDateTime
)
