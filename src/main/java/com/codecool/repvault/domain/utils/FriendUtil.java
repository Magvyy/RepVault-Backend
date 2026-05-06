package com.codecool.repvault.domain.utils;

import com.codecool.repvault.domain.entities.User;
import com.codecool.repvault.domain.entities.ids.FriendId;
import com.codecool.repvault.infrastructure.repositories.FriendRepository;
import org.springframework.stereotype.Component;

@Component
public class FriendUtil {
    private final SecurityUtil securityUtil;
    private final FriendRepository friendRepository;

    public FriendUtil(SecurityUtil securityUtil, FriendRepository friendRepository) {
        this.securityUtil = securityUtil;
        this.friendRepository = friendRepository;
    }

    public boolean isFriendsWith(User user) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        FriendId id = new FriendId(authenticatedUser.getId(), user.getId());
        return friendRepository.existsById(id);
    }
}
