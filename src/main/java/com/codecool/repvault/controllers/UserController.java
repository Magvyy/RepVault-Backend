package com.codecool.repvault.controllers;

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO;
import com.codecool.repvault.application.DTOs.outgoing.UserResponseDTO;
import com.codecool.repvault.controllers.utils.ResponseUtil;
import com.codecool.repvault.domain.entities.User;
import com.codecool.repvault.domain.services.UserService;
import org.springframework.http.HttpStatus;
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
        User user = userService.readUser(id);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return ResponseUtil.wrapEntity(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.updateUser(id, userRequestDTO);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return ResponseUtil.wrapEntity(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
    }
}
