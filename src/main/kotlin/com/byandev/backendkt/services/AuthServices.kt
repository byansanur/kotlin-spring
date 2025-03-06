package com.byandev.backendkt.services

import com.byandev.backendkt.model.req.UsersLoginRequest
import com.byandev.backendkt.model.res.UsersLoginResponse

interface AuthServices {
    fun loginByPassword(loginRequest: UsersLoginRequest) : UsersLoginResponse?

//    fun registration()
}