package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.exceptions.UserException;
import com.codecool.cashtrack.infrastructure.repositories.UserRepository;
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

    public User throwIfUserDoesNotExist(Long id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isEmpty()) throw new UserException("User does not exist");
        return oUser.get();
    }

    public boolean authenticatedUserHasId(Long id) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        return authenticatedUser.getId().equals(id);
    }

    public void validateAndSet(User user, UserRequestDTO userRequestDTO) {
        String userName = userRequestDTO.getUserName();
        String password = userRequestDTO.getPassword();
        if (isValidUserName(userName)) user.setUserName(userName);
        if (isValidPassword(password)) user.setPassword(passwordEncoder.encode(password));
    }

    private boolean isValidUserName(String userName) {
        return !userName.trim().isEmpty();
    }

    private boolean isValidPassword(String userName) {
        return !userName.trim().isEmpty();
    }
}
