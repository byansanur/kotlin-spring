package com.byandev.backendkt.model.res

data class RefreshTokenResponse(
    val accessToken: String, val refreshToken: String
)
