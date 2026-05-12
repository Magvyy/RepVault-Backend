package com.codecool.repvault.infrastructure.repositories

import com.codecool.repvault.domain.entities.Friend
import com.codecool.repvault.domain.entities.ids.FriendId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendRepository : JpaRepository<Friend, FriendId> {
    override fun existsById(id: FriendId): Boolean
}
