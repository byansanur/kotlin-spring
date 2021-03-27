package com.byandev.backendkt.model

data class CreateUsersRequest(
        val id: Long,
        val name: String,
        val userName: String,
        val passwords: String
)
