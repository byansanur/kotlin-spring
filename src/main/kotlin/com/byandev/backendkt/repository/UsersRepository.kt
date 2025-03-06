package com.byandev.backendkt.repository

import com.byandev.backendkt.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long> {
    fun existsByUsername(userName: String): Boolean
}