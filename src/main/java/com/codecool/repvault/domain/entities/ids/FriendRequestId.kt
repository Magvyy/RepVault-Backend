package com.codecool.repvault.domain.entities.ids

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*


@Embeddable
class FriendRequestId : Serializable {
    @Column(name = "from_user_id", nullable = false)
    var from: Long? = null // user id

    @Column(name = "to_user_id", nullable = false)
    var to: Long? = null // user id

    constructor(from: Long, to: Long) {
        this.from = from
        this.to = to
    }

    constructor()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null) return false
        if (o !is FriendRequestId) return false
        return from == o.from && to == o.to
    }

    override fun hashCode(): Int {
        return Objects.hash(from, to)
    }
}