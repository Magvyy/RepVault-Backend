package com.codecool.repvault.application.security

import com.codecool.repvault.application.DTOs.outgoing.ErrorResponseDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.exceptions.FriendException
import com.codecool.repvault.domain.exceptions.SessionException
import com.codecool.repvault.domain.exceptions.UserException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserException::class)
    fun handleUserException(exception: UserException): ResponseEntity<ErrorResponseDTO> {
        return ResponseUtil.wrapEntity<ErrorResponseDTO>(ErrorResponseDTO(exception.message), exception.status!!)
    }

    @ExceptionHandler(FriendException::class)
    fun handleFriendException(exception: FriendException): ResponseEntity<ErrorResponseDTO> {
        return ResponseUtil.wrapEntity<ErrorResponseDTO>(ErrorResponseDTO(exception.message), exception.status!!)
    }

    @ExceptionHandler(SessionException::class)
    fun handleSessionException(exception: SessionException): ResponseEntity<ErrorResponseDTO> {
        return ResponseUtil.wrapEntity<ErrorResponseDTO>(ErrorResponseDTO(exception.message), exception.status!!)
    }
}