package com.example.common

data class PagingModel<T>(val data: T, val total: Int, val currentPage: Int)