package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.TemplateExercise
import com.codecool.repvault.domain.entities.TemplateSession
import com.codecool.repvault.domain.entities.enums.ExerciseEnum

class SessionOverviewResponseDTO(templateSession: TemplateSession) {
    val id: Long = templateSession.id!!
    val name: String = templateSession.name!!
    val exercises: MutableList<ExerciseEnum> = templateSession.exercises.stream()
        .map<ExerciseEnum> { obj: TemplateExercise -> obj.type }
        .toList()
}
