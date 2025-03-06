package com.byandev.backendkt.controller

import com.byandev.backendkt.model.WebResponse
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class ErrorControllers: ErrorController {
    @RequestMapping(value = ["/error"], produces = ["application/json"])
    fun handleError(request: HttpServletRequest): ResponseEntity<WebResponse<Nothing?>> {
        val status = request.getAttribute("javax.servlet.error.status_code") as Int
        val errorResponse = WebResponse(
            apiStatus = status,
            apiMessage = if (status == 404) "Not found." else "An error occurred.",
            data = null
        )
        return ResponseEntity(errorResponse, HttpStatus.valueOf(status))
    }

    override fun getErrorPath(): String {
        return "/error"
    }
}