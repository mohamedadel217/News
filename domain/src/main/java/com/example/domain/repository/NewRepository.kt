package com.example.domain.repository

import com.example.common.PagingModel
import com.example.domain.entity.NewEntity
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    suspend fun getNews(page: Int): Flow<PagingModel<List<NewEntity>>>

}