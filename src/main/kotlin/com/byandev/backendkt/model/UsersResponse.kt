package com.byandev.backendkt.model

import java.util.*

data class UsersResponse(
        val id: Long,
        val name: String,
        val userName: String,
        val passwords: String,
        val createdAt: Date,
        val updatedAt: Date?
)
