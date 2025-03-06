package com.byandev.backendkt.services.impl

import com.byandev.backendkt.repository.AuthRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val authRepository: AuthRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = authRepository.findFirstByName(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        return User.builder()
            .username(user.username)
            .password(user.passwords) // Assuming passwords are already hashed
            .roles("USER") // You can add roles here
            .build()
    }

}