package com.codecool.cashtrack.application.security;


import com.codecool.cashtrack.application.DTOs.outgoing.ErrorResponseDTO;
import com.codecool.cashtrack.controllers.utils.ResponseUtil;
import com.codecool.cashtrack.domain.exceptions.FriendException;
import com.codecool.cashtrack.domain.exceptions.SessionException;
import com.codecool.cashtrack.domain.exceptions.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserException(UserException exception) {
        return ResponseUtil.wrapEntity(new ErrorResponseDTO(exception.getMessage()), exception.getStatus());
    }

    @ExceptionHandler(FriendException.class)
    public ResponseEntity<ErrorResponseDTO> handleFriendException(FriendException exception) {
        return ResponseUtil.wrapEntity(new ErrorResponseDTO(exception.getMessage()), exception.getStatus());
    }

    @ExceptionHandler(SessionException.class)
    public ResponseEntity<ErrorResponseDTO> handleSessionException(SessionException exception) {
        return ResponseUtil.wrapEntity(new ErrorResponseDTO(exception.getMessage()), exception.getStatus());
    }
}