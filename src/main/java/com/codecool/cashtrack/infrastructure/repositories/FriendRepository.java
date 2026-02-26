package com.codecool.cashtrack.infrastructure.repositories;

import com.codecool.cashtrack.domain.entities.Friend;
import com.codecool.cashtrack.domain.entities.ids.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    boolean existsById(FriendId id);
}
