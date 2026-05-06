package com.codecool.repvault.controllers;

import com.codecool.repvault.controllers.utils.ResponseUtil;
import com.codecool.repvault.domain.services.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> sendRequest(@PathVariable Long id) {
        friendService.sendFriendRequest(id);
        return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accept/{id}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long id) {
        friendService.acceptFriendRequest(id);
        return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id) {
        friendService.rejectFriendRequest(id);
        return ResponseUtil.wrapEntity(null, HttpStatus.NO_CONTENT);
    }

}
