package com.codecool.cashtrack.application.DTOs.outgoing;

import com.codecool.cashtrack.domain.entities.enums.SetEnum;
import lombok.Getter;

@Getter
public class SetTypeResponseDTO {
    private Long id;
    private SetEnum type;

    public SetTypeResponseDTO(Long id, SetEnum type) {
        this.id = id;
        this.type = type;
    }
}
