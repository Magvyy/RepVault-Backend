package com.codecool.repvault.application.security.filters

import com.codecool.repvault.application.security.custom.CustomUserDetailsService
import com.codecool.repvault.application.security.utils.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.jspecify.annotations.NullMarked
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtFilter(private val jwtUtil: JwtUtil, @param:Value($$"${jwt.name}") private val jwtCookieName: String, private val userDetailsService: CustomUserDetailsService) : OncePerRequestFilter() {
    @NullMarked
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = extractJwtFromCookies(request)
        if (jwt == null) {
            filterChain.doFilter(request, response)
            return
        }

        if (jwtUtil.validateToken(jwt)) {
            val username = jwtUtil.extractUsername(jwt)
            val userDetails = userDetailsService.loadUserByUsername(username)
            val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authToken
        }

        filterChain.doFilter(request, response)
    }

    private fun extractJwtFromCookies(request: HttpServletRequest): String? {
        if (request.cookies == null) {
            return null
        }

        for (cookie in request.cookies) {
            if (jwtCookieName == cookie.name) {
                return cookie.value
            }
        }
        return null
    }
}
