package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.outgoing.ExerciseTypeDTO
import com.codecool.repvault.application.DTOs.outgoing.SetTypeDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.entities.enums.ExerciseEnum
import com.codecool.repvault.domain.entities.enums.SetEnum
import com.codecool.repvault.domain.services.TypeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/types")
class TypeController(private val typeService: TypeService) {
    @get:GetMapping("/sets")
    val setTypes: ResponseEntity<MutableList<SetTypeDTO>>
        get() {
            val setTypeResponseDTOS = typeService.setTypes
                .stream()
                .map<SetTypeDTO> { type: SetEnum -> SetTypeDTO(type.ordinal.toLong(), type) }
                .toList()
            return ResponseUtil.wrapEntity<MutableList<SetTypeDTO>>(setTypeResponseDTOS)
        }

    @get:GetMapping("/exercises")
    val exerciseTypes: ResponseEntity<MutableList<ExerciseTypeDTO>>
        get() {
            val setTypeResponseDTOS = typeService.exerciseTypes
                .stream()
                .map<ExerciseTypeDTO> { type: ExerciseEnum -> ExerciseTypeDTO(type.ordinal.toLong(), type) }
                .toList()
            return ResponseUtil.wrapEntity<MutableList<ExerciseTypeDTO>>(setTypeResponseDTOS)
        }
}
