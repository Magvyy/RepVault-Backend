package com.codecool.repvault.infrastructure.repositories

import com.codecool.repvault.domain.entities.TemplateSession
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateSessionRepository : JpaRepository<TemplateSession, Long> {
    fun findByUserId(userId: Long, pageable: Pageable): MutableList<TemplateSession>
}
