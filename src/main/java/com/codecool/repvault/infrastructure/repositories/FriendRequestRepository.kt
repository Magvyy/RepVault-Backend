package com.codecool.repvault.infrastructure.repositories

import com.codecool.repvault.domain.entities.FriendRequest
import com.codecool.repvault.domain.entities.User
import com.codecool.repvault.domain.entities.ids.FriendRequestId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FriendRequestRepository : JpaRepository<FriendRequest, FriendRequestId> {
    fun existsById(id: FriendRequest): Boolean

    fun findByToAndFrom(to: User, from: User): Optional<FriendRequest>

    @Query(
        value = """
        SELECT EXISTS (
            SELECT 1
            FROM friend_requests
            WHERE (from_id = :userId1 AND to_id = :userId2)
            OR (from_id = :userId2 AND to_id = :userId1)
        )
    
    """, nativeQuery = true
    )
    fun existsBetween(@Param("userId1") userId1: Long, @Param("userId2") userId2: Long): Boolean
}
