package com.codecool.repvault.infrastructure.repositories

import com.codecool.repvault.domain.entities.ActiveSession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActiveSessionRepository : JpaRepository<ActiveSession, Long> {
    fun existsByUserId(userId: Long): Boolean
    fun findByUserId(userId: Long): ActiveSession
}
