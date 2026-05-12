package com.codecool.repvault.infrastructure.repositories

import com.codecool.repvault.domain.entities.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository : JpaRepository<Session, Long> {

    @Query(
        value = """
            WITH user_friends AS (
                SELECT user_id_1, user_id_2
                FROM friends
                WHERE user_id_1 = :userId
                OR user_id_2 = :userId
            ), visible_authors AS (
                SELECT
                    CASE
                        WHEN user_id_1 = :userId THEN user_id_2
                        ELSE user_id_1
                    END AS user_id
                FROM user_friends
                UNION ALL
                SELECT :userId AS user_id
            )
            SELECT s.*
            FROM sessions s
            JOIN visible_authors v
            ON s.user_id = v.user_id
            ORDER BY s.id DESC
            OFFSET :offset LIMIT :pageSize
    """, nativeQuery = true
    )
    fun findVisible(userId: Long, offset: Int, pageSize: Int): MutableList<Session>

    @Query(
        value = """
            SELECT *
            FROM sessions
            WHERE user_id = :friendId
            AND (
                :userId = :friendId OR
                EXISTS (
                    SELECT 1
                    FROM friends
                    WHERE (user_id_1 = :userId AND user_id_2 = :friendId)
                    OR (user_id_2 = :userId AND user_id_1 = :friendId)
                )
            )
            ORDER BY id DESC
            OFFSET :offset LIMIT :pageSize
    """, nativeQuery = true
    )
    fun findByUserIdIfFriends(userId: Long, friendId: Long, offset: Int, pageSize: Int): MutableList<Session>
}
