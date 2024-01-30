package com.example.remote.model

import com.squareup.moshi.Json

data class SourceNetwork(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null
)