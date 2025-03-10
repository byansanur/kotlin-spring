package com.byandev.backendkt.repository

import com.byandev.backendkt.entity.UsersToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : JpaRepository<UsersToken, Long> {
    fun findByUsername(username: String): UsersToken?

    @Query("SELECT u FROM UsersToken u WHERE u.token = :token")
    fun findByOldToken(token: String): UsersToken?
}