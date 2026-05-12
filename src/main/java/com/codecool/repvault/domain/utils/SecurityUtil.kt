package com.codecool.repvault.domain.utils

import com.codecool.repvault.domain.entities.User
import com.codecool.repvault.domain.exceptions.UserException
import com.codecool.repvault.infrastructure.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class SecurityUtil(private val userRepository: UserRepository) {
    val authenticatedUser: User
        get() {
            val authentication =
                SecurityContextHolder.getContext().authentication
            if (authentication != null && authentication.principal is UserDetails) {
                val oUser =
                    userRepository.findByUserName((authentication.principal as UserDetails).username)
                if (oUser.isEmpty) throw UserException(
                    "User is authenticated, but not in database",
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
                return oUser.get()
            }
            throw UserException("User is unauthenticated", HttpStatus.FORBIDDEN)
        }
}
