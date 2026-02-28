package com.codecool.cashtrack.application.DTOs.outgoing;

import com.codecool.cashtrack.domain.entities.Session;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class SessionResponseDTO {
    private Long id;
    private String description;
    private List<ExerciseResponseDTO> exercises;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public SessionResponseDTO(Session session) {
        this.id = session.getId();
        this.description = session.getDescription();
        this.exercises = session.getExercises().stream()
                .map(ExerciseResponseDTO::new)
                .toList();
        this.start = session.getStart();
        this.end = session.getEnd();
    }
}
