package com.example.data.repository

import com.example.common.Mapper
import com.example.common.PagingModel
import com.example.data.model.NewDTO
import com.example.domain.entity.NewEntity
import com.example.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val newMapper: Mapper<NewDTO, NewEntity>
) : NewRepository {

    override suspend fun getNews(page: Int): Flow<PagingModel<List<NewEntity>>> {
        return flow {
            try {
                val data = remoteDataSource.getNews(page)
                emit(
                    PagingModel(
                        data = newMapper.fromList(data.data),
                        total = data.total,
                        currentPage = page
                    )
                )

            } catch (ex: Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val localData = localDataSource.getNews()
                    emit(
                        PagingModel(
                            data = newMapper.fromList(localData.data),
                            total = localData.total,
                            currentPage = page
                        )
                    )
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    throw ex
                }
            }
        }
    }
}