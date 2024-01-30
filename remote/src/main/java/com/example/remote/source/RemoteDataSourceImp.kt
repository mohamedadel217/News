package com.example.remote.source

import com.example.common.Mapper
import com.example.common.PagingModel
import com.example.data.model.NewDTO
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.remote.model.NewNetwork
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService : ApiService,
    private val newMapper : Mapper<NewNetwork, NewDTO>
) : RemoteDataSource {

    override suspend fun getNews(page: Int): PagingModel<List<NewDTO>> {
        val data = apiService.getNews(page = page)
        return PagingModel(
            data = newMapper.fromList(data.articles),
            total = data.totalResults ?: 0,
            currentPage = page
        )
    }

}