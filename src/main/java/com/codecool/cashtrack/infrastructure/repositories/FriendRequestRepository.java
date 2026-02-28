package com.codecool.cashtrack.infrastructure.repositories;

import com.codecool.cashtrack.domain.entities.FriendRequest;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.entities.ids.FriendRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, FriendRequestId> {

    boolean existsById(FriendRequest id);

    Optional<FriendRequest> findByToAndFrom(User to, User from);

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM friend_requests
            WHERE (from_id = :userId1 AND to_id = :userId2)
            OR (from_id = :userId2 AND to_id = :userId1)
        )
    """, nativeQuery = true)
    boolean existsBetween(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}
