package com.example.local.source

import com.example.common.Mapper
import com.example.common.PagingModel
import com.example.data.model.NewDTO
import com.example.data.repository.LocalDataSource
import com.example.local.database.NewsDao
import com.example.local.model.NewLocal
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val newsDao: NewsDao ,
    private val newMapper : Mapper<NewLocal, NewDTO>
) : LocalDataSource {

    override suspend fun getNews(): PagingModel<List<NewDTO>> {
        val data = newsDao.getAllNews()
        return PagingModel(
            data = newMapper.fromList(data),
            total = 1, // get only first page from local database if there is no internet
            currentPage = 0
        )
    }

}