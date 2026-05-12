package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO
import com.codecool.repvault.application.DTOs.outgoing.UserResponseDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping("/{id}")
    fun readUser(@PathVariable id: Long): ResponseEntity<UserResponseDTO> {
        val user = userService.readUser(id)
        val userResponseDTO = UserResponseDTO(user)
        return ResponseUtil.wrapEntity<UserResponseDTO>(userResponseDTO)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userRequestDTO: UserRequestDTO
    ): ResponseEntity<UserResponseDTO> {
        val user = userService.updateUser(id, userRequestDTO)
        val userResponseDTO = UserResponseDTO(user)
        return ResponseUtil.wrapEntity<UserResponseDTO>(userResponseDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<*> {
        userService.deleteUser(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }
}
