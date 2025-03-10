package com.byandev.backendkt.services.impl

import com.byandev.backendkt.entity.UsersToken
import com.byandev.backendkt.model.res.RefreshTokenResponse
import com.byandev.backendkt.repository.RefreshTokenRepository
import com.byandev.backendkt.security.JwtUtil
import com.byandev.backendkt.services.RefreshTokenService
import com.byandev.backendkt.utils.formatNowTime
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.util.*

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtUtil: JwtUtil
) : RefreshTokenService {
    override fun isTokenNotExpired(token: String): Boolean {
        println("refExpired: ${jwtUtil.extractExpiration(token)?.before(Date())}")
        val timeNow = DateFormat.getTimeInstance().parse(formatNowTime())
        return jwtUtil.extractExpiration(token)?.before(Date()) != false
    }

    override fun updateRefreshToken(oldToken: String, refreshToken: String): RefreshTokenResponse {
        val userTokenData = refreshTokenRepository.findByOldToken(oldToken)
        println("oldToken: $oldToken")
        val newToken = UsersToken(
            id = userTokenData?.id.toString(),
            username = userTokenData?.username.toString(),
            token = userTokenData?.token.toString(),
            refreshToken = refreshToken,
            createdAt = userTokenData?.createdAt.toString(),
            updatedAt = formatNowTime(),
            deletedAt = null
        )
        refreshTokenRepository.save(newToken)
        println("newToken: $newToken")
        return RefreshTokenResponse(accessToken = newToken.token, refreshToken = newToken.refreshToken.toString())
    }
}