package com.codecool.repvault.infrastructure.repositories

import com.codecool.repvault.domain.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserName(userName: String): Optional<User>
    override fun findById(id: Long): Optional<User>
}
