package com.codecool.repvault.application.DTOs.incoming

import java.time.ZonedDateTime

data class ActiveSessionRequestDTO (
    val name: String,
    val description: String?,
    val exercises: MutableList<ActiveExerciseRequestDTO>,
    val start: ZonedDateTime,
    val public: Boolean
)
