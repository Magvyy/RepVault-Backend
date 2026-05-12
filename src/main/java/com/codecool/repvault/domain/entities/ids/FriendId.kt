package com.codecool.repvault.domain.entities.ids

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*


@Embeddable
class FriendId : Serializable {
    @Column(name = "from_user_id", nullable = false)
    var user1: Long? = null

    @Column(name = "to_user_id", nullable = false)
    var user2: Long? = null

    constructor(user1: Long, user2: Long) {
        if (user1 < user2) {
            this.user1 = user1
            this.user2 = user2
        } else {
            this.user1 = user2
            this.user2 = user1
        }
    }

    constructor()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null) return false
        if (o !is FriendId) return false
        return user1 == o.user1 && user2 == o.user2
    }

    override fun hashCode(): Int {
        return Objects.hash(user1, user2)
    }
}