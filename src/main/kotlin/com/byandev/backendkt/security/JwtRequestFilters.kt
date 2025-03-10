package com.byandev.backendkt.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilters(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader("Authorization")

        var username: String? = null
        var jwt: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7)
            try {
                username = jwtUtil.extractUsername(jwt)
            } catch (e: ExpiredJwtException) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Token Expired")
                return // Stop further processing
            } catch (e: Exception) {
                // Handle other JWT exceptions (e.g., invalid signature)
                response.status = HttpServletResponse.SC_FORBIDDEN
                return // Stop further processing
            }
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {

            val userDetails = this.userDetailsService.loadUserByUsername(username)

            if (jwtUtil.validateToken(jwt!!, userDetails)) {

                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        chain.doFilter(request, response)
    }
}