package com.byandev.backendkt.model

data class WebResponse<T>(
        val apiStatus: Int,
        val apiMessage: String,
        val data: T
)