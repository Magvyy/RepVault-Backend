package com.codecool.repvault.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "username", nullable = false)
    var userName: String? = null

    @Column(name = "password", nullable = false)
    var password: String? = null

    constructor(id: Long, userName: String, password: String) {
        this.id = id
        this.userName = userName
        this.password = password
    }

    constructor(userName: String, password: String?) {
        this.userName = userName
        this.password = password
    }

    constructor()
}
