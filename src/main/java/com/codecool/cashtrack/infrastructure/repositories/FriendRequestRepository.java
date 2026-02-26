package com.codecool.cashtrack.infrastructure.repositories;

import com.codecool.cashtrack.domain.entities.FriendRequest;
import com.codecool.cashtrack.domain.entities.ids.FriendRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, FriendRequestId> {

    boolean existsById(FriendRequest id);
}
