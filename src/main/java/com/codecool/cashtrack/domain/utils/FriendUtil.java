package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.entities.ids.FriendId;
import com.codecool.cashtrack.infrastructure.repositories.FriendRepository;
import org.springframework.stereotype.Component;

@Component
public class FriendUtil {
    private final SecurityUtil securityUtil;
    private final FriendRepository friendRepository;

    public FriendUtil(SecurityUtil securityUtil, FriendRepository friendRepository) {
        this.securityUtil = securityUtil;
        this.friendRepository = friendRepository;
    }

    public boolean areFriends(User user1, User user2) {
        FriendId id = new FriendId(user1.getId(), user2.getId());
        return friendRepository.existsById(id);
    }
}
