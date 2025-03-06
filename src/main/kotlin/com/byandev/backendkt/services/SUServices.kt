package com.byandev.backendkt.services

import com.byandev.backendkt.model.req.CreateRoleRequest
import com.byandev.backendkt.model.res.UserRolesResponse

interface SUServices {
    fun createRoleBySuperUsers(request: CreateRoleRequest) : String
    fun getRoles() : MutableList<UserRolesResponse>?
}