package com.example.data.repository

import com.example.common.PagingModel
import com.example.data.model.NewDTO

interface RemoteDataSource {

    suspend fun getNews(page: Int): PagingModel<List<NewDTO>>

}