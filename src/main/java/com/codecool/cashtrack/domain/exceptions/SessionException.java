package com.codecool.cashtrack.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SessionException extends RuntimeException {
    private HttpStatus status;

    public SessionException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
