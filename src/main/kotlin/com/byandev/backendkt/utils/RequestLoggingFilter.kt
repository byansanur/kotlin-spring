package com.byandev.backendkt.utils

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//@Component
//class RequestLoggingFilter : OncePerRequestFilter() {
//
//    private val logger = LoggerFactory.getLogger(RequestLoggingFilter::class.java)
//
//
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        logger.info("Received request: {${request.method}} {${request.requestURI}}")
//        filterChain.doFilter(request, response)
//    }
//}