package com.codecool.cashtrack.domain.services;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.utils.UserUtil;
import com.codecool.cashtrack.infrastructure.repositories.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    public UserService(UserRepository userRepository, UserUtil userUtil) {
        this.userRepository = userRepository;
        this.userUtil = userUtil;
    }

    public User createUser(UserRequestDTO userRequestDTO) {
        userUtil.validate(userRequestDTO);
        User user = userUtil.convertToEntity(userRequestDTO);
        return userRepository.save(user);
    }

    public User readUser(Long id) {
        return userUtil.findByIdOrThrow(id);
    }

    public User updateUser(Long id, UserRequestDTO userRequestDTO) {
        userUtil.validate(userRequestDTO);
        User user = userUtil.findByIdOrThrow(id);
        if (!userUtil.authenticatedUserHasId(id)) throw new AccessDeniedException("Unauthorized user update");
        userUtil.set(user, userRequestDTO);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userUtil.findByIdOrThrow(id);
        if (!userUtil.authenticatedUserHasId(id)) throw new AccessDeniedException("Unauthorized user deletion");
        userRepository.delete(user);
    }
}
