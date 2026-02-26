package com.codecool.cashtrack.domain.services;

import com.codecool.cashtrack.application.DTOs.incoming.UserRequestDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.ResponseDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.UserResponseDTO;
import com.codecool.cashtrack.domain.utils.UserUtil;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.exceptions.UserException;
import com.codecool.cashtrack.infrastructure.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserUtil userUtil;
    private final UserRepository userRepository;

    public UserService(UserUtil userUtil, UserRepository userRepository) {
        this.userUtil = userUtil;
        this.userRepository = userRepository;
    }

    public ResponseEntity<ResponseDTO> createUser(UserRequestDTO userRequestDTO) {
        User user = userUtil.convertToEntity(userRequestDTO);
        if (userUtil.isUserNameTaken(user.getUserName())) throw new UserException("Username is taken");
        userRepository.save(user);
        return ResponseEntity.ok(new ResponseDTO("Registration Successful"));
    }

    public ResponseEntity<UserResponseDTO> readUser(Long id) {
        User user = userUtil.throwIfUserDoesNotExist(id);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    public ResponseEntity<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userUtil.throwIfUserDoesNotExist(id);
        if (!userUtil.authenticatedUserHasId(id)) throw new AccessDeniedException("Unauthorized user update");
        userUtil.validateAndSet(user, userRequestDTO);
        user = userRepository.save(user);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    public ResponseEntity<ResponseDTO> deleteUser(Long id) {
        User user = userUtil.throwIfUserDoesNotExist(id);
        if (!userUtil.authenticatedUserHasId(id)) throw new AccessDeniedException("Unauthorized user deletion");
        userRepository.delete(user);
        return ResponseEntity.ok(new ResponseDTO("User deletion successful"));
    }
}
