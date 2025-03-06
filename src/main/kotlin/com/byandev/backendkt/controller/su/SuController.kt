package com.byandev.backendkt.controller.su

import com.byandev.backendkt.model.WebResponse
import com.byandev.backendkt.model.req.CreateRoleRequest
import com.byandev.backendkt.model.req.UsersLoginRequest
import com.byandev.backendkt.model.res.UserRolesResponse
import com.byandev.backendkt.model.res.UsersLoginResponse
import com.byandev.backendkt.services.SUServices
import com.byandev.backendkt.services.UsersServices
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/su-conf")
class SuController(val suServices: SUServices) {
    @PostMapping(
        value = ["/roles"],
        produces = ["application/json"],
        consumes = ["application/json"],
    )
    fun createRole(
        @RequestBody
        body: CreateRoleRequest
    ) : WebResponse<String?> {
        val response = suServices.createRoleBySuperUsers(body)
        return WebResponse(
            apiStatus = 200,
            apiMessage = "Success",
            data = response
        )
    }

    @GetMapping(
        value = ["/roles"],
        produces = ["application/json"]
    )
    fun getRoles() : WebResponse<out MutableList<UserRolesResponse>?> {
        val response = suServices.getRoles()
        return if (!response.isNullOrEmpty()) {
            WebResponse(
                apiStatus = 200,
                apiMessage = "Success",
                data = response
            )
        } else {
            WebResponse(
                apiStatus = HttpStatus.NOT_FOUND.value(),
                apiMessage = HttpStatus.NOT_FOUND.name,
                data = null
            )
        }
    }
}