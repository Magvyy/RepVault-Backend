package com.codecool.repvault.domain.services

import com.codecool.repvault.domain.entities.Friend
import com.codecool.repvault.domain.entities.FriendRequest
import com.codecool.repvault.domain.exceptions.FriendException
import com.codecool.repvault.domain.utils.FriendRequestUtil
import com.codecool.repvault.domain.utils.FriendUtil
import com.codecool.repvault.domain.utils.SecurityUtil
import com.codecool.repvault.domain.utils.UserUtil
import com.codecool.repvault.infrastructure.repositories.FriendRepository
import com.codecool.repvault.infrastructure.repositories.FriendRequestRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class FriendService(
    private val friendRequestRepository: FriendRequestRepository,
    private val friendRepository: FriendRepository,
    private val friendRequestUtil: FriendRequestUtil,
    private val friendUtil: FriendUtil,
    private val userUtil: UserUtil,
    private val securityUtil: SecurityUtil
) {
    fun sendFriendRequest(userId: Long) {
        val user = userUtil.findByIdOrThrow(userId)

        if (userUtil.authenticatedUserHasId(userId)) throw FriendException(
            "You can't send a friend request to yourself",
            HttpStatus.BAD_REQUEST
        )
        if (friendRequestUtil.hasRequestWith(user)) throw FriendException(
            "Friend request already exists",
            HttpStatus.BAD_REQUEST
        )
        if (friendUtil.isFriendsWith(user)) throw FriendException("Already friends", HttpStatus.BAD_REQUEST)

        val authenticatedUser = securityUtil.authenticatedUser
        val friendRequest = FriendRequest(authenticatedUser, user)
        friendRequestRepository.save<FriendRequest>(friendRequest)
    }

    fun acceptFriendRequest(userId: Long) {
        val user = userUtil.findByIdOrThrow(userId)
        val authenticatedUser = securityUtil.authenticatedUser

        val friendRequest = friendRequestRepository
            .findByToAndFrom(authenticatedUser, user)
            .orElseThrow<FriendException>(Supplier {
                FriendException(
                    "You don't have a friend request from this user",
                    HttpStatus.NOT_FOUND
                )
            })

        friendRequestRepository.delete(friendRequest)
        val friend = Friend(authenticatedUser, user)
        friendRepository.save<Friend>(friend)
    }

    fun rejectFriendRequest(userId: Long) {
        val user = userUtil.findByIdOrThrow(userId)
        val authenticatedUser = securityUtil.authenticatedUser

        val friendRequest = friendRequestRepository
            .findByToAndFrom(authenticatedUser, user)
            .orElseThrow<FriendException>(Supplier {
                FriendException(
                    "You don't have a friend request from this user",
                    HttpStatus.NOT_FOUND
                )
            })

        friendRequestRepository.delete(friendRequest)
    }
}
