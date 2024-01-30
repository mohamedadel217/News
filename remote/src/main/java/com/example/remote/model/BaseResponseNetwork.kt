package com.example.remote.model

import com.squareup.moshi.Json

open class BaseResponseNetwork(
    @Json(name = "status") val status: String? = null,
    @Json(name = "totalResults") val totalResults: Int? = 0,
    @Json(name = "code") val code: String? = null,
    @Json(name = "message") val message: String? = null
)