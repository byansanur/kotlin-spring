package com.byandev.backendkt.controller

import com.byandev.backendkt.model.req.CreateUsersRequest
import com.byandev.backendkt.model.res.UsersResponse
import com.byandev.backendkt.model.WebResponse
import com.byandev.backendkt.services.UsersServices
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/pub")
class UsersController(val usersServices: UsersServices) {

    @PostMapping(
            value = ["/registration"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createUser(
            @RequestBody
            body: CreateUsersRequest
    ) : WebResponse<UsersResponse?> {
        val usersResponse = usersServices.create(body)

        return if (usersResponse != null) {
            WebResponse(
                apiStatus = 201,
                apiMessage = "Success",
                data = usersResponse
            )
        } else {
            WebResponse(
                apiStatus = HttpStatus.CONFLICT.value(),
                apiMessage = "Username already exists",
                data = null
            )
        }

    }

}