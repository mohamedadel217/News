package com.example.domain.usecase

import com.example.base.BaseUseCase
import com.example.common.PagingModel
import com.example.domain.dispatcher.DispatcherProvider
import com.example.domain.entity.NewEntity
import com.example.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newRepository: NewRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseUseCase<PagingModel<List<NewEntity>>, Int>() {

    override suspend fun buildRequest(params: Int?): Flow<PagingModel<List<NewEntity>>> {
        if (params == null) {
            return flow<PagingModel<List<NewEntity>>> {
                throw Exception("page can't be null")
            }.flowOn(dispatcherProvider.io())
        }
        return newRepository.getNews(params).flowOn(dispatcherProvider.io())
    }

}