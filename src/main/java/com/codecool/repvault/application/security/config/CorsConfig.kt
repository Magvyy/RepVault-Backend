package com.codecool.repvault.application.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
open class CorsConfig {
    @Value($$"${api.front-end}")
    private val frontend: String? = null

    @Bean
    open fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()

        config.allowedOrigins = listOf<String>(frontend!!)
        config.allowedMethods = mutableListOf<String>("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowedHeaders = mutableListOf<String>("*")
        config.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }
}