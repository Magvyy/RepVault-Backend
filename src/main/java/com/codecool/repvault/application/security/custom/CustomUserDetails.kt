package com.codecool.repvault.application.security.custom

import com.codecool.repvault.domain.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
//        for (Role role : user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
//        }
        return authorities
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String {
        return user.userName!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
