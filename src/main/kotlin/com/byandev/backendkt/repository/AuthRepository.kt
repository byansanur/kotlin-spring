package com.byandev.backendkt.repository

import com.byandev.backendkt.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


interface AuthRepository : JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.username = :username")
    fun findFirstByName(@Param("username") username: String?): Users?
}