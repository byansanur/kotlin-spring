package com.byandev.backendkt.services

import com.byandev.backendkt.model.res.RefreshTokenResponse

interface RefreshTokenService {
    fun isTokenNotExpired(token: String) : Boolean

    fun updateRefreshToken(oldToken: String, refreshToken: String) : RefreshTokenResponse
}