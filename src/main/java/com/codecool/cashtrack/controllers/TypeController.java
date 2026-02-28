package com.codecool.cashtrack.controllers;

import com.codecool.cashtrack.application.DTOs.outgoing.ExerciseTypeResponseDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.SetTypeResponseDTO;
import com.codecool.cashtrack.controllers.utils.ResponseUtil;
import com.codecool.cashtrack.domain.services.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/sets")
    public ResponseEntity<List<SetTypeResponseDTO>> getSetTypes() {
        List<SetTypeResponseDTO> setTypeResponseDTOS = typeService.getSetTypes()
                .stream()
                .map(type -> new SetTypeResponseDTO((long) type.ordinal(), type))
                .toList();
        return ResponseUtil.wrapEntity(setTypeResponseDTOS);
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseTypeResponseDTO>> getExerciseTypes() {
        List<ExerciseTypeResponseDTO> setTypeResponseDTOS = typeService.getExerciseTypes()
                .stream()
                .map(type -> new ExerciseTypeResponseDTO((long) type.ordinal(), type))
                .toList();
        return ResponseUtil.wrapEntity(setTypeResponseDTOS);
    }
}
