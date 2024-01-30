package com.example.domain.entity

data class NewEntity(
    val sourceEntity: SourceEntity,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)