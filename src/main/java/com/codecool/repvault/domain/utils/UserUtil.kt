package com.codecool.repvault.domain.utils

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO
import com.codecool.repvault.domain.entities.User
import com.codecool.repvault.domain.exceptions.UserException
import com.codecool.repvault.infrastructure.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserUtil(
    private val securityUtil: SecurityUtil,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun convertToEntity(userRequestDTO: UserRequestDTO): User {
        return User(
            userRequestDTO.userName,
            passwordEncoder.encode(userRequestDTO.password)
        )
    }

    fun isUserNameTaken(userName: String): Boolean {
        val oUser = userRepository.findByUserName(userName)
        return oUser.isPresent
    }

    fun findByIdOrThrow(id: Long): User {
        val oUser = userRepository.findById(id)
        if (oUser.isEmpty) throw UserException("User does not exist", HttpStatus.NOT_FOUND)
        return oUser.get()
    }

    fun authenticatedUserHasId(id: Long): Boolean {
        val authenticatedUser = securityUtil.authenticatedUser
        return authenticatedUser.id == id
    }

    fun validate(userRequestDTO: UserRequestDTO) {
        val userName = userRequestDTO.userName
        val password = userRequestDTO.password
        if (!isValidUserName(userName)) throw UserException("Invalid username", HttpStatus.BAD_REQUEST)
        if (!isValidPassword(password)) throw UserException("Invalid password", HttpStatus.BAD_REQUEST)
    }

    fun set(user: User, userRequestDTO: UserRequestDTO) {
        user.userName = (userRequestDTO.userName)
        user.password = passwordEncoder.encode(userRequestDTO.password)
    }

    private fun isValidUserName(userName: String): Boolean {
        if (isUserNameTaken(userName)) throw UserException("Username is taken", HttpStatus.CONFLICT)
        return !userName.trim { it <= ' ' }.isEmpty()
    }

    private fun isValidPassword(userName: String): Boolean {
        return !userName.trim { it <= ' ' }.isEmpty()
    }

    fun userExists(userId: Long): Boolean {
        return userRepository.existsById(userId)
    }
}
