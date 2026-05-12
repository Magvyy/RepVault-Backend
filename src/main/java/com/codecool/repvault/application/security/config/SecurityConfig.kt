package com.codecool.repvault.application.security.config

import com.codecool.repvault.application.security.custom.JwtAuthenticationProvider
import com.codecool.repvault.application.security.filters.JwtFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
open class SecurityConfig(
    private val jwtFilter: JwtFilter,
    @param:Value("\${jwt.name}") private val jwtCookieName: String,
    private val jwtAuthenticationProvider: JwtAuthenticationProvider
) {
    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf(Customizer { csrf: CsrfConfigurer<HttpSecurity?>? -> csrf!!.disable() })
            .sessionManagement(Customizer { session: SessionManagementConfigurer<HttpSecurity?>? ->
                session!!.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            )
            .authenticationProvider(jwtAuthenticationProvider) //.anonymous(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests(Customizer { authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/types/**").permitAll()
                    .requestMatchers("/v3/api-docs").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .anyRequest().authenticated()
            }
            )
            .logout(Customizer { logout: LogoutConfigurer<HttpSecurity?>? ->
                logout!!
                    .logoutUrl("/auth/logout")
                    .deleteCookies(jwtCookieName)
                    .logoutSuccessUrl("/")
            }
            )
        return http.build()
    }
}
