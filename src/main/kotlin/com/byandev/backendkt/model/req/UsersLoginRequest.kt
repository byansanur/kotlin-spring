package com.byandev.backendkt.model.req

import org.jetbrains.annotations.NotNull

data class UsersLoginRequest(
        @NotNull
        val username: String?,
        @NotNull
        val passwords: String
)
