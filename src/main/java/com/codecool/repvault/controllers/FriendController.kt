package com.codecool.repvault.controllers

import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.services.FriendService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/friends")
class FriendController(private val friendService: FriendService) {
    @GetMapping("/request/{id}")
    fun sendRequest(@PathVariable id: Long): ResponseEntity<*> {
        friendService.sendFriendRequest(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }

    @GetMapping("/accept/{id}")
    fun acceptRequest(@PathVariable id: Long): ResponseEntity<*> {
        friendService.acceptFriendRequest(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }

    @GetMapping("/reject/{id}")
    fun rejectRequest(@PathVariable id: Long): ResponseEntity<*> {
        friendService.rejectFriendRequest(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }
}
