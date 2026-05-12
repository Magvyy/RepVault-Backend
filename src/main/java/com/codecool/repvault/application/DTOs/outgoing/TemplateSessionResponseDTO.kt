package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.TemplateExercise
import com.codecool.repvault.domain.entities.TemplateSession


class TemplateSessionResponseDTO(templateSession: TemplateSession) {
    val id: Long = templateSession.id!!
    val name: String = templateSession.name!!
    val exercises: MutableList<TemplateExerciseResponseDTO> = templateSession.exercises.stream()
        .map<TemplateExerciseResponseDTO> { templateExercise: TemplateExercise ->
            TemplateExerciseResponseDTO(
                templateExercise
            )
        }
        .toList()
}
