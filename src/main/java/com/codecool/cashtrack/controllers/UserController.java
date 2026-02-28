package com.codecool.cashtrack.controllers;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.DefaultResponseDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.UserResponseDTO;
import com.codecool.cashtrack.domain.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> readUser(@PathVariable Long id) {
        return userService.readUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(id, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
