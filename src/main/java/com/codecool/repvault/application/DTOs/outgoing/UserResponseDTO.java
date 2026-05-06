package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.User;
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
