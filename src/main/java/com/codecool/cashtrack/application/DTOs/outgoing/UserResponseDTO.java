package com.codecool.cashtrack.application.DTOs.outgoing;

import com.codecool.cashtrack.domain.entities.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private Long id;
    private String userName;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }
}
