package com.codecool.repvault.controllers

import com.codecool.repvault.application.DTOs.incoming.UserRequestDTO
import com.codecool.repvault.application.DTOs.outgoing.UserRelationResponseDTO
import com.codecool.repvault.application.DTOs.outgoing.UserResponseDTO
import com.codecool.repvault.controllers.utils.ResponseUtil
import com.codecool.repvault.domain.services.UserService
import com.codecool.repvault.domain.utils.FriendRequestUtil
import com.codecool.repvault.domain.utils.FriendUtil
import com.codecool.repvault.domain.utils.SecurityUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService, private val securityUtil: SecurityUtil, private val friendUtil: FriendUtil, private val friendRequestUtil: FriendRequestUtil) {
    @GetMapping("/{id}")
    fun readUser(@PathVariable id: Long): ResponseEntity<UserRelationResponseDTO> {
        val user = userService.readUser(id)
        val auth = securityUtil.authenticatedUser
        val canAccept = if (auth == null) false else friendRequestUtil.hasRequestFrom(user)
        val canAdd = if (auth == null) false else if (friendUtil.isFriendsWith(user)) false else !canAccept && auth.id != user.id
        val userRelationResponseDTO = UserRelationResponseDTO(user, canAdd, canAccept)
        return ResponseUtil.wrapEntity<UserRelationResponseDTO>(userRelationResponseDTO)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userRequestDTO: UserRequestDTO
    ): ResponseEntity<UserResponseDTO> {
        val user = userService.updateUser(id, userRequestDTO)
        val userResponseDTO = UserResponseDTO(user)
        return ResponseUtil.wrapEntity<UserResponseDTO>(userResponseDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<*> {
        userService.deleteUser(id)
        return ResponseUtil.wrapEntity<Any>(null, HttpStatus.NO_CONTENT)
    }
}
