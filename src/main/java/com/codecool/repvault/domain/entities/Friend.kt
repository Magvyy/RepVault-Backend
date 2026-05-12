package com.codecool.repvault.domain.entities

import com.codecool.repvault.domain.entities.ids.FriendId
import jakarta.persistence.*

@Entity
@Table(name = "friends")
class Friend {
    @EmbeddedId
    var id: FriendId? = null

    @MapsId("user1")
    @ManyToOne
    @JoinColumn(name = "user_id_1", nullable = false)
    var user1: User? = null

    @MapsId("user2")
    @ManyToOne
    @JoinColumn(name = "user_id_2", nullable = false)
    var user2: User? = null

    constructor(a: User, b: User) {
        if (a.id!! < b.id!!) {
            this.user1 = a
            this.user2 = b
        } else {
            this.user1 = b
            this.user2 = a
        }
        this.id = FriendId(this.user1!!.id!!, this.user2!!.id!!)
    }

    constructor()
}