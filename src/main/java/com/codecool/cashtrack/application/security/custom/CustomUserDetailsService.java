package com.codecool.cashtrack.application.security.custom;

import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.exceptions.UserException;
import com.codecool.cashtrack.infrastructure.repositories.UserRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UserException("User not found", HttpStatus.NOT_FOUND));
        return new CustomUserDetails(user);
    }

}
