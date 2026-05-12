package com.codecool.repvault.domain.utils

import com.codecool.repvault.domain.entities.User
import com.codecool.repvault.domain.entities.ids.FriendRequestId
import com.codecool.repvault.infrastructure.repositories.FriendRequestRepository
import org.springframework.stereotype.Component

@Component
class FriendRequestUtil(
    private val securityUtil: SecurityUtil,
    private val friendRequestRepository: FriendRequestRepository
) {
    fun hasRequestWith(user: User): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser
        return friendRequestRepository.existsBetween(user.id!!, authenticatedUser.id!!)
    }

    fun hasRequestFrom(user: User): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser
        val id = FriendRequestId(user.id!!, authenticatedUser.id!!)
        return friendRequestRepository.existsById(id)
    }

    fun hasRequestTo(user: User): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser
        val id = FriendRequestId(authenticatedUser.id!!, user.id!!)
        return friendRequestRepository.existsById(id)
    }
}
