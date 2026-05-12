package com.codecool.repvault.domain.services

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO
import com.codecool.repvault.domain.entities.User
import com.codecool.repvault.domain.utils.UserUtil
import com.codecool.repvault.infrastructure.repositories.UserRepository
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val userUtil: UserUtil) {
    fun createUser(userRequestDTO: UserRequestDTO): User {
        userUtil.validate(userRequestDTO)
        val user = userUtil.convertToEntity(userRequestDTO)
        return userRepository.save<User>(user)
    }

    fun readUser(id: Long): User {
        return userUtil.findByIdOrThrow(id)
    }

    fun updateUser(id: Long, userRequestDTO: UserRequestDTO): User {
        userUtil.validate(userRequestDTO)
        val user = userUtil.findByIdOrThrow(id)

        if (!userUtil.authenticatedUserHasId(id)) throw AccessDeniedException("Unauthorized user update")

        userUtil.set(user, userRequestDTO)
        return userRepository.save<User>(user)
    }

    fun deleteUser(id: Long) {
        val user = userUtil.findByIdOrThrow(id)

        if (!userUtil.authenticatedUserHasId(id)) throw AccessDeniedException("Unauthorized user deletion")

        userRepository.delete(user)
    }
}
