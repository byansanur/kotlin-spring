package com.byandev.backendkt.services

import com.byandev.backendkt.model.CreateUsersRequest
import com.byandev.backendkt.model.UsersResponse

interface UsersServices {

    fun create(createUsersRequest: CreateUsersRequest) : UsersResponse
}