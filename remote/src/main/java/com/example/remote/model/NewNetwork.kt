package com.example.remote.model

import com.squareup.moshi.Json

data class NewNetwork(
    @Json(name = "source") val source: SourceNetwork? = null,
    @Json(name = "author") val author: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "urlToImage") val urlToImage: String? = null,
    @Json(name = "publishedAt") val publishedAt: String? = null,
    @Json(name =  "content") val content: String? = null
)
