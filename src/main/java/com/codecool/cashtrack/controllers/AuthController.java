package com.codecool.cashtrack.controllers;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.controllers.utils.JwtUtil;
import com.codecool.cashtrack.controllers.utils.ResponseUtil;
import com.codecool.cashtrack.domain.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        userService.createUser(userRequestDTO);
        jwtUtil.authenticate(userRequestDTO);
        return ResponseUtil.wrapEntity(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequestDTO) {
        jwtUtil.authenticate(userRequestDTO);
        return ResponseUtil.wrapEntity(null);
    }
}
