package com.codecool.repvault.application.DTOs.outgoing;


import com.codecool.repvault.domain.entities.enums.SetEnum;
import lombok.Getter;

@Getter
public class SetTypeDTO {
    private Long id;
    private SetEnum type;

    public SetTypeDTO(Long id, SetEnum type) {
        this.id = id;
        this.type = type;
    }
}