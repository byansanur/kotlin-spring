package com.byandev.backendkt.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class Users(
        @Id
        val id: Long,
        @Column(name ="name")
        val name: String,
        @Column(name ="user_name")
        val userName: String,
        @Column(name ="passwords")
        val passwords: String,
        @Column(name ="created_at")
        val createdAt: Date,
        @Column(name ="updated_at")
        val updatedAt: Date?
)
