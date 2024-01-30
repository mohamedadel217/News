package com.example.data.repository

import com.example.common.PagingModel
import com.example.data.model.NewDTO

interface LocalDataSource {

    suspend fun getNews(): PagingModel<List<NewDTO>>

}