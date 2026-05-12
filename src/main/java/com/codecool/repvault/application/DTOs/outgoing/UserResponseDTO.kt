package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.User


class UserResponseDTO(user: User) {
    val id: Long = user.id!!
    val userName: String = user.userName!!
}
