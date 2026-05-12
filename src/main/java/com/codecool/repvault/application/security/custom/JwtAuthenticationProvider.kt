package com.codecool.repvault.application.security.custom

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    private val customUserDetailsService: CustomUserDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication? {
        val userName = authentication.name
        val password: String? = authentication.getCredentials().toString()

        val userDetails = customUserDetailsService.loadUserByUsername(userName)

        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("Incorrect password")
        }

        return UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities())
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}