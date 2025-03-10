package com.byandev.backendkt.entity

import javax.persistence.*

@Entity
@Table(name = "users_token")
data class UsersToken(
    @Id
    val id: String,
    @Column(nullable = false, name = "username")
    val username: String,
    @Column(nullable = false, unique = true)
    var token: String,
    @Column(name = "refresh_token")
    var refreshToken: String?,
    @Column(nullable = true, name = "created_at")
    val createdAt: String,
    @Column(nullable = true, name = "updated_at")
    val updatedAt: String?,
    @Column(nullable = true, name = "deleted_at")
    val deletedAt: String?,
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    var user: Users
)