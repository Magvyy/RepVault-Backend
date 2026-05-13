package com.codecool.repvault.application.DTOs.outgoing

import com.codecool.repvault.domain.entities.User


class UserRelationResponseDTO(user: User, canAdd: Boolean, canAccept: Boolean) {
    val id: Long = user.id!!
    val userName: String = user.userName!!
    val canAdd: Boolean = canAdd
    val canAccept: Boolean = canAccept
}