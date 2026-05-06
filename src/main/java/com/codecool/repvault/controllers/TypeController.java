package com.codecool.repvault.controllers;

import com.codecool.repvault.application.DTOs.outgoing.ExerciseTypeDTO;
import com.codecool.repvault.application.DTOs.outgoing.SetTypeDTO;
import com.codecool.repvault.controllers.utils.ResponseUtil;
import com.codecool.repvault.domain.services.TypeService;
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
    public ResponseEntity<List<SetTypeDTO>> getSetTypes() {
        List<SetTypeDTO> setTypeResponseDTOS = typeService.getSetTypes()
                .stream()
                .map(type -> new SetTypeDTO((long) type.ordinal(), type))
                .toList();
        return ResponseUtil.wrapEntity(setTypeResponseDTOS);
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseTypeDTO>> getExerciseTypes() {
        List<ExerciseTypeDTO> setTypeResponseDTOS = typeService.getExerciseTypes()
                .stream()
                .map(type -> new ExerciseTypeDTO((long) type.ordinal(), type))
                .toList();
        return ResponseUtil.wrapEntity(setTypeResponseDTOS);
    }
}
