package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO
import com.codecool.repvault.application.security.custom.CustomUserDetails
import com.codecool.repvault.application.security.utils.JwtUtil
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.services.UserService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {
    @PostMapping("/register")
    fun register(@RequestBody userRequestDTO: UserRequestDTO, response: HttpServletResponse): ResponseEntity<*> {
        userService.createUser(userRequestDTO)
        return authenticate(userRequestDTO, response)
    }

    @PostMapping("/login")
    fun login(@RequestBody userRequestDTO: UserRequestDTO, response: HttpServletResponse): ResponseEntity<*> {
        return authenticate(userRequestDTO, response)
    }

    private fun authenticate(userRequestDTO: UserRequestDTO, response: HttpServletResponse): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userRequestDTO.userName, userRequestDTO.password)
        )

        val principal = authentication.principal
        if (principal is CustomUserDetails) {
            val user = principal.user
            val jwt = jwtUtil.generateToken(user.userName)
            val jwtCookie = Cookie("access_token", jwt)
            jwtCookie.isHttpOnly = true
            jwtCookie.secure = false
            jwtCookie.path = "/"
            jwtCookie.maxAge = 24 * 60 * 60
            response.addCookie(jwtCookie)
            return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
        }

        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.UNAUTHORIZED)
    }
}
