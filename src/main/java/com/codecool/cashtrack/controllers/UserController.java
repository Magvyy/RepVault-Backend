package com.codecool.cashtrack.controllers;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.UserResponseDTO;
import com.codecool.cashtrack.controllers.utils.ResponseUtil;
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
        UserResponseDTO userResponseDTO = userService.readUser(id);
        return ResponseUtil.wrapEntity(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(id, userRequestDTO);
        return ResponseUtil.wrapEntity(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseUtil.wrapEntity(null);
    }
}
