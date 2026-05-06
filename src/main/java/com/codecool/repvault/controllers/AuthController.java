package com.codecool.repvault.controllers;

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO;
import com.codecool.repvault.application.security.custom.CustomUserDetails;
import com.codecool.repvault.application.security.utils.JwtUtil;
import com.codecool.repvault.controllers.utils.ResponseUtil;
import com.codecool.repvault.domain.entities.User;
import com.codecool.repvault.domain.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO, HttpServletResponse response) {
        userService.createUser(userRequestDTO);
        return authenticate(userRequestDTO, response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequestDTO, HttpServletResponse response) {
        return authenticate(userRequestDTO, response);
    }

    private ResponseEntity<?> authenticate(UserRequestDTO userRequestDTO, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.getUserName(), userRequestDTO.getPassword())
        );

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails details) {
            User user = details.getUser();
            String jwt = jwtUtil.generateToken(user.getUserName());
            Cookie jwtCookie = new Cookie("access_token", jwt);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60);
            response.addCookie(jwtCookie);
            return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
        }

        return ResponseUtil.wrapEntity(null, HttpStatus.UNAUTHORIZED);
    }
}
