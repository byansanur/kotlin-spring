package com.byandev.backendkt.utils

import com.byandev.backendkt.model.WebResponse
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<WebResponse<Nothing?>> {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage ?: "Validation error" }.joinToString(", ")
        val errorResponse = WebResponse(
            apiStatus = HttpStatus.BAD_REQUEST.value(),
            apiMessage = errors,
            data = null
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissingParameterException(ex: MissingKotlinParameterException): ResponseEntity<WebResponse<Nothing?>> {
        val errorResponse = WebResponse<Nothing?>(
            apiStatus = HttpStatus.BAD_REQUEST.value(),
            apiMessage = "Missing required parameter: ${ex.parameter.name}",
            data = null
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<WebResponse<Nothing?>> {
        val errorResponse = WebResponse<Nothing?>(
            apiStatus = HttpStatus.BAD_REQUEST.value(),
            apiMessage = "Invalid request body or missing required parameter.",
            data = null
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    // Generic handler should be last
    @ExceptionHandler(Exception::class)
    fun handleGenericExceptions(ex: Exception): ResponseEntity<WebResponse<Nothing?>> {
        val errorResponse = WebResponse(
            apiStatus = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            apiMessage = "An unexpected error occurred: ${ex.message}", // Include exception message for debugging
            data = null
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}