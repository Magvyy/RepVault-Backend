package com.codecool.cashtrack.application.DTOs.outgoing;

import lombok.Getter;

@Getter
public class ResponseDTO {
    private String message;

    public ResponseDTO(String message) {
        this.message = message;
    }
}
