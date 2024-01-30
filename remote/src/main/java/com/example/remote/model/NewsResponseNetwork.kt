package com.example.remote.model

import com.squareup.moshi.Json

data class NewsResponseNetwork(
    @Json(name = "articles") val articles: List<NewNetwork>? = null
): BaseResponseNetwork()
