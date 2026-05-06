package com.codecool.repvault.domain.services;

import com.codecool.repvault.domain.entities.Friend;
import com.codecool.repvault.domain.entities.FriendRequest;
import com.codecool.repvault.domain.entities.User;
import com.codecool.repvault.domain.exceptions.FriendException;
import com.codecool.repvault.domain.utils.FriendRequestUtil;
import com.codecool.repvault.domain.utils.FriendUtil;
import com.codecool.repvault.domain.utils.SecurityUtil;
import com.codecool.repvault.domain.utils.UserUtil;
import com.codecool.repvault.infrastructure.repositories.FriendRepository;
import com.codecool.repvault.infrastructure.repositories.FriendRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class FriendService {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;
    private final FriendRequestUtil friendRequestUtil;
    private final FriendUtil friendUtil;
    private final UserUtil userUtil;
    private final SecurityUtil securityUtil;

    public FriendService(FriendRequestRepository friendRequestRepository, FriendRepository friendRepository, FriendRequestUtil friendRequestUtil, FriendUtil friendUtil, UserUtil userUtil, SecurityUtil securityUtil) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendRepository = friendRepository;
        this.friendRequestUtil = friendRequestUtil;
        this.friendUtil = friendUtil;
        this.userUtil = userUtil;
        this.securityUtil = securityUtil;
    }

    public void sendFriendRequest(Long userId) {
        User user = userUtil.findByIdOrThrow(userId);

        if (userUtil.authenticatedUserHasId(userId)) throw new FriendException("You can't send a friend request to yourself", HttpStatus.BAD_REQUEST);
        if (friendRequestUtil.hasRequestWith(user)) throw new FriendException("Friend request already exists", HttpStatus.BAD_REQUEST);
        if (friendUtil.isFriendsWith(user)) throw new FriendException("Already friends", HttpStatus.BAD_REQUEST);

        User authenticatedUser = securityUtil.getAuthenticatedUser();
        FriendRequest friendRequest = new FriendRequest(authenticatedUser, user);
        friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(Long userId) {
        User user = userUtil.findByIdOrThrow(userId);
        User authenticatedUser = securityUtil.getAuthenticatedUser();

        FriendRequest friendRequest = friendRequestRepository
                .findByToAndFrom(authenticatedUser, user)
                .orElseThrow(() -> new FriendException("You don't have a friend request from this user", HttpStatus.NOT_FOUND));

        friendRequestRepository.delete(friendRequest);
        Friend friend = new Friend(authenticatedUser, user);
        friendRepository.save(friend);
    }

    public void rejectFriendRequest(Long userId) {
        User user = userUtil.findByIdOrThrow(userId);
        User authenticatedUser = securityUtil.getAuthenticatedUser();

        FriendRequest friendRequest = friendRequestRepository
                .findByToAndFrom(authenticatedUser, user)
                .orElseThrow(() -> new FriendException("You don't have a friend request from this user", HttpStatus.NOT_FOUND));

        friendRequestRepository.delete(friendRequest);
    }
}
