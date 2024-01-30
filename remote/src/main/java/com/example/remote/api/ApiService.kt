package com.example.remote.api

import com.example.remote.model.NewsResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") sources: String = "bbc-news",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10
    ): NewsResponseNetwork

}