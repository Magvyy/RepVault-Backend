package com.codecool.cashtrack.application.DTOs.outgoing;

import lombok.Getter;

@Getter
public class DefaultResponseDTO {
    private String message;

    public DefaultResponseDTO(String message) {
        this.message = message;
    }
}
