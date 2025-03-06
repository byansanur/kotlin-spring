package com.byandev.backendkt.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class Users(
        @Id
        val id: String,
        @Column(name ="name")
        val name: String,
        @Column(name ="user_name")
        val username: String,
        @Column(name ="passwords")
        val passwords: String,
        @Column(name ="created_at")
        val createdAt: String,
        @Column(name ="updated_at")
        val updatedAt: String?,
        @Column(name = "role")
        val role: String?
)
