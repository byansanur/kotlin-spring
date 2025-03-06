package com.byandev.backendkt.services

import com.byandev.backendkt.model.req.CreateUsersRequest
import com.byandev.backendkt.model.res.UsersResponse

interface UsersServices {

    fun create(createUsersRequest: CreateUsersRequest) : UsersResponse?
}