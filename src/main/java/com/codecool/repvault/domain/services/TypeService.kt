package com.codecool.repvault.domain.services

import com.codecool.repvault.domain.entities.enums.ExerciseEnum
import com.codecool.repvault.domain.entities.enums.SetEnum
import org.springframework.stereotype.Service

@Service
class TypeService {
    val setTypes: MutableList<SetEnum>
        get() = SetEnum.entries.toTypedArray().toList() as MutableList<SetEnum>

    val exerciseTypes: MutableList<ExerciseEnum>
        get() = ExerciseEnum.entries.toTypedArray().toList() as MutableList<ExerciseEnum>
}
