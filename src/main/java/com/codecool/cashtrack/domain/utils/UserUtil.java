package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.exceptions.UserException;
import com.codecool.cashtrack.infrastructure.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtil {
    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserUtil(SecurityUtil securityUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.securityUtil = securityUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(UserRequestDTO userRequestDTO) {
        return new User(
                userRequestDTO.getUserName(),
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
    }

    public boolean isUserNameTaken(String userName) {
        Optional<User> oUser = userRepository.findByUserName(userName);
        return oUser.isPresent();
    }

    public User findByIdOrThrow(Long id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isEmpty()) throw new UserException("User does not exist", HttpStatus.NOT_FOUND);
        return oUser.get();
    }

    public boolean authenticatedUserHasId(Long id) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        return authenticatedUser.getId().equals(id);
    }

    public void validate(UserRequestDTO userRequestDTO) {
        String userName = userRequestDTO.getUserName();
        String password = userRequestDTO.getPassword();
        if (!isValidUserName(userName)) throw new UserException("Invalid username", HttpStatus.BAD_REQUEST);
        if (!isValidPassword(password)) throw new UserException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public void set(User user, UserRequestDTO userRequestDTO) {
        user.setUserName(userRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    }

    private boolean isValidUserName(String userName) {
        if (isUserNameTaken(userName)) throw new UserException("Username is taken", HttpStatus.CONFLICT);
        return !userName.trim().isEmpty();
    }

    private boolean isValidPassword(String userName) {
        return !userName.trim().isEmpty();
    }

    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }
}
