package com.codecool.cashtrack.application.DTOs.outgoing;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {
    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
