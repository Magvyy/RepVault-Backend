package com.codecool.repvault.domain.entities;

import com.codecool.repvault.domain.entities.ids.FriendRequestId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "friend_requests")
public class FriendRequest {

    @EmbeddedId
    private FriendRequestId id;

    @MapsId("from")
    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private User from;

    @MapsId("to")
    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private User to;

    public FriendRequest(User from, User to) {
        this.id = new FriendRequestId(from.getId(), to.getId());
        this.from = from;
        this.to = to;
    }

    public FriendRequest() {

    }
}