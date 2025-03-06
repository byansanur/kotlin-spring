package com.byandev.backendkt.controller

import com.byandev.backendkt.model.WebResponse
import com.byandev.backendkt.model.req.UsersLoginRequest
import com.byandev.backendkt.model.res.UsersLoginResponse
import com.byandev.backendkt.security.JwtUtil
import com.byandev.backendkt.services.AuthServices
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/pub")
class AuthController(
    val authServices: AuthServices
) {

    @PostMapping(
        value = ["/login-password"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun loginPassword(
        @RequestBody
        body: UsersLoginRequest
    ) : WebResponse<UsersLoginResponse?> {
        val authResponse = authServices.loginByPassword(body)
        return if (authResponse != null) {
            WebResponse(
                apiStatus = 200,
                apiMessage = "Success",
                data = authResponse
            )
        } else {
            WebResponse(
                apiStatus = HttpStatus.UNAUTHORIZED.value(),
                apiMessage = "Invalid username or password",
                data = null
            )
        }
    }
}