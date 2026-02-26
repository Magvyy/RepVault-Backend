package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.entities.ids.FriendRequestId;
import com.codecool.cashtrack.infrastructure.repositories.FriendRequestRepository;
import org.springframework.stereotype.Component;

@Component
public class FriendRequestUtil {
    private final SecurityUtil securityUtil;
    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestUtil(SecurityUtil securityUtil, FriendRequestRepository friendRequestRepository) {
        this.securityUtil = securityUtil;
        this.friendRequestRepository = friendRequestRepository;
    }

    public boolean hasRequestFrom(User user) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        FriendRequestId id = new FriendRequestId(user.getId(), authenticatedUser.getId());
        return friendRequestRepository.existsById(id);
    }

    public boolean hasRequestTo(User user) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        FriendRequestId id = new FriendRequestId(authenticatedUser.getId(), user.getId());
        return friendRequestRepository.existsById(id);
    }
}
