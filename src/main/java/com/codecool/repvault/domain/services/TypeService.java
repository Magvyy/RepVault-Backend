package com.codecool.repvault.domain.services;

import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import com.codecool.repvault.domain.entities.enums.SetEnum;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeService {

    public TypeService() {

    }

    public List<SetEnum> getSetTypes() {
        return List.of(SetEnum.values());
    }

    public List<ExerciseEnum> getExerciseTypes() {
        return List.of(ExerciseEnum.values());
    }
}
