package com.byandev.backendkt.controller

import com.byandev.backendkt.model.CreateUsersRequest
import com.byandev.backendkt.model.UsersResponse
import com.byandev.backendkt.model.WebResponse
import com.byandev.backendkt.services.UsersServices
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UsersController(val usersServices: UsersServices) {

    @PostMapping(
            value = ["/user"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createUser(
            @RequestBody
            body: CreateUsersRequest
    ) : WebResponse<UsersResponse> {
        val usersResponse = usersServices.create(body)

        return WebResponse(
                apiStatus = 201,
                apiMessage = "OK",
                data = usersResponse
        )

    }

}