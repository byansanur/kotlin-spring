package com.byandev.backendkt.repository

import com.byandev.backendkt.entity.UserRoles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface URoleRepository : JpaRepository<UserRoles, Long> {
    @Query("SELECT u FROM UserRoles u WHERE u.roleNumber = :roleNumber")
    fun getRoleByNumber(roleNumber: Int?) : UserRoles?
}