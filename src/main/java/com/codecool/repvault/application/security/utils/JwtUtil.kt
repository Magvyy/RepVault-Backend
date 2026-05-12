package com.codecool.repvault.application.security.utils

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(@Value($$"${jwt.key}") secret_key: String?) {
    private val KEY: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key))

    @JvmOverloads
    fun generateToken(userName: String?, role: String? = null): String? {
        val claims: MutableMap<String?, Any?> = HashMap<String?, Any?>()
        if (role != null) {
            claims["role"] = role
        }
        return Jwts.builder()
            .claims(claims)
            .subject(userName)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(KEY)
            .compact()
    }

    fun extractUsername(token: String?): String {
        return Jwts.parser()
            .verifyWith(KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .subject
    }

    fun extractRole(token: String?): String? {
        return Jwts.parser()
            .verifyWith(KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload()["role"] as String?
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
            return true
        } catch (e: JwtException) {
            System.err.println(e)
            return false
        }
    }
}