package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.enums.SetEnum



class SetResponseDTO(val id: Long, val type: SetEnum, val reps: Int, val weight: Double) {}