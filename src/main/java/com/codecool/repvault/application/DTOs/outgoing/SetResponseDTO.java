package com.codecool.repvault.application.DTOs.outgoing;

import com.codecool.repvault.domain.entities.Set;
import com.codecool.repvault.domain.entities.SetTemplate;
import com.codecool.repvault.domain.entities.enums.SetEnum;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SetResponseDTO {
    private Long id;
    private SetEnum type;
    private int reps;
    private BigDecimal weight;

    public SetResponseDTO(Set set) {
        this.id = set.getId();
        this.type = set.getType();
        this.reps = set.getReps();
        this.weight = set.getWeight();
    }

    public SetResponseDTO(SetTemplate setTemplate) {
        this.id = setTemplate.getId();
        this.type = setTemplate.getType();
        this.reps = setTemplate.getReps();
        this.weight = setTemplate.getWeight();
    }
}
