package com.codecool.repvault.domain.utils

import com.codecool.repvault.domain.entities.User
import com.codecool.repvault.domain.entities.ids.FriendId
import com.codecool.repvault.infrastructure.repositories.FriendRepository
import org.springframework.stereotype.Component

@Component
class FriendUtil(private val securityUtil: SecurityUtil, private val friendRepository: FriendRepository) {
    fun isFriendsWith(user: User): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser
        val id = FriendId(authenticatedUser.id!!, user.id!!)
        return friendRepository.existsById(id)
    }
}
