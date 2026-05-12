package com.codecool.repvault.application.security.custom

import com.codecool.repvault.domain.exceptions.UserException
import com.codecool.repvault.infrastructure.repositories.UserRepository
import org.jspecify.annotations.NullMarked
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class CustomUserDetailsService(val userRepository: UserRepository, passwordEncoder: PasswordEncoder) : UserDetailsService {
    @NullMarked
    override fun loadUserByUsername(userName: String): UserDetails {
        val user = userRepository.findByUserName(userName).orElseThrow<UserException>(Supplier { UserException("User not found", HttpStatus.NOT_FOUND) })
        return CustomUserDetails(user)
    }
}
