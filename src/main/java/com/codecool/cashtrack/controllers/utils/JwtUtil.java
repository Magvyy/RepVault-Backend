package com.codecool.cashtrack.controllers.utils;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.application.security.custom.JwtAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final JwtAuthenticationManager jwtAuthenticationManager;

    public JwtUtil(JwtAuthenticationManager jwtAuthenticationManager) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }


    public void authenticate(UserRequestDTO userRequestDTO) {
        Authentication authentication = jwtAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.getUserName(), userRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
