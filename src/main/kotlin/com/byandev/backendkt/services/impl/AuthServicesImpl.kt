package com.byandev.backendkt.services.impl

import com.byandev.backendkt.model.req.UsersLoginRequest
import com.byandev.backendkt.model.res.UsersLoginResponse
import com.byandev.backendkt.repository.AuthRepository
import com.byandev.backendkt.repository.URoleRepository
import com.byandev.backendkt.security.JwtUtil
import com.byandev.backendkt.services.AuthServices
import com.byandev.backendkt.utils.formatNowTime
import com.byandev.backendkt.utils.formatUserRolesFromString
import com.byandev.backendkt.utils.formatUserRolesToJson
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServicesImpl(
    val authRepository: AuthRepository,
    val passwordEncoder: PasswordEncoder,
    val authenticationManager: AuthenticationManager,
    val userDetailsService: UserDetailsService,
    val roleRepository: URoleRepository,
    val jwtUtil: JwtUtil
) : AuthServices {
    override fun loginByPassword(loginRequest: UsersLoginRequest): UsersLoginResponse? {
        println("login request: $loginRequest")

        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.passwords)
            )
        } catch (e: Exception) {
            // Authentication failed
            println("AuthService failed: ${e.message}")
            return null
        }

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(loginRequest.username)

        val userData = authRepository.findFirstByName(loginRequest.username) ?: return null

        val updatedUser = userData.copy(updatedAt = formatNowTime()) // Create a copy with the updated field
        authRepository.save(updatedUser) // Save the updated entity


        var jsonUserRole = ""
        if (userData.role != null) {
            val roles = formatUserRolesFromString(userData.role)
            val userRole = roleRepository.getRoleByNumber(roles.roleNumber)
            if (userRole != null) {
                jsonUserRole = formatUserRolesToJson(userRole)
                println("roleId: $jsonUserRole")
            }
        }

        val token = jwtUtil.generateToken(userDetails)
        println("token: $token")

        return UsersLoginResponse(
            id = updatedUser.id,
            name = updatedUser.name,
            username = updatedUser.username,
            createdAt = updatedUser.createdAt,
            updatedAt = updatedUser.updatedAt,
            roles = formatUserRolesFromString(jsonUserRole),
            token = token
        )
    }

    private fun verifyPassword(plainTextPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainTextPassword, hashedPassword)
    }
}