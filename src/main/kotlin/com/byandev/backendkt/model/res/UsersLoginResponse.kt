package com.byandev.backendkt.model.res

import com.byandev.backendkt.entity.UserRoles
import java.util.*

data class UsersLoginResponse(
        val id: String,
        val name: String,
        val username: String,
        val createdAt: String,
        val updatedAt: String?,
        val roles: UserRoles?,
        val token: String
)
