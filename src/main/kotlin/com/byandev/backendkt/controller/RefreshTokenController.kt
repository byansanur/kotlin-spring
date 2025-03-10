package com.byandev.backendkt.controller

import com.byandev.backendkt.model.WebResponse
import com.byandev.backendkt.model.res.RefreshTokenResponse
import com.byandev.backendkt.security.JwtUtil
import com.byandev.backendkt.services.RefreshTokenService
import io.jsonwebtoken.Claims
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/pub")
class RefreshTokenController(
    private val jwtUtil: JwtUtil,
    private val refreshTokenService: RefreshTokenService,
    private val userDetailsService: UserDetailsService
) {
    @PostMapping(
        value = ["/refreshToken"],
    )
    fun refreshToken(@RequestHeader("Authorization") authorizationHeader: String) : WebResponse<RefreshTokenResponse?> {
        println("header: $authorizationHeader")
        val refreshToken = authorizationHeader.substringAfter("Bearer ")
        // Validate refresh token
        val claims: Claims? = jwtUtil.extractAllClaims(refreshToken)
        println("claims: $claims")

        if (refreshTokenService.isTokenNotExpired(refreshToken)) {
            return WebResponse(
                apiStatus = HttpStatus.BAD_REQUEST.value(),
                apiMessage = HttpStatus.BAD_REQUEST.name,
                data = null
            )
        }

        val username = claims?.subject

        // Generate new tokens
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
        val newAccessToken = jwtUtil.generateRefreshToken(userDetails)

        // Update refresh token in database
        val refresh = refreshTokenService.updateRefreshToken(refreshToken, newAccessToken)

        return WebResponse(
            apiStatus = 200,
            apiMessage = "Success",
            data = refresh
        )
    }
}