package com.byandev.backendkt.model.req

import org.jetbrains.annotations.NotNull

data class CreateUsersRequest(
        @NotNull
        val name: String,
        val username: String?,
        @NotNull
        val passwords: String,
        val roleNumber: Int?
)
