package com.codecool.repvault.domain.entities

import com.codecool.repvault.domain.entities.ids.FriendRequestId
import jakarta.persistence.*


@Entity
@Table(name = "friend_requests")
class FriendRequest {
    @EmbeddedId
    var id: FriendRequestId? = null

    @MapsId("from")
    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    var from: User? = null

    @MapsId("to")
    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    var to: User? = null

    constructor(from: User, to: User) {
        this.id = FriendRequestId(from.id!!, to.id!!)
        this.from = from
        this.to = to
    }

    constructor()
}