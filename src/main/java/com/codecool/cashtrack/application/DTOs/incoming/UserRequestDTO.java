package com.codecool.cashtrack.application.DTOs.incoming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String userName;
    private String password;
}
