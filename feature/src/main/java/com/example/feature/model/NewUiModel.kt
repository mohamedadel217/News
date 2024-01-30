package com.example.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewUiModel(
    val sourceUiModel: SourceUiModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
): Parcelable
