package com.example.news.di

import com.example.common.Mapper
import com.example.data.mapper.NewDataDomainMapper
import com.example.data.model.NewDTO
import com.example.domain.entity.NewEntity
import com.example.feature.mapper.NewDomainUiMapper
import com.example.feature.model.NewUiModel
import com.example.local.mapper.NewLocalDataMapper
import com.example.local.model.NewLocal
import com.example.remote.mapper.NewNetworkDataMapper
import com.example.remote.model.NewNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Data Mappers
    @Binds
    abstract fun bindsNewDataDomainMapper(mapper: NewDataDomainMapper): Mapper<NewDTO, NewEntity>
    //endregion

    //region Remote Mappers
    @Binds
    abstract fun bindsNewNetworkDataMapper(mapper: NewNetworkDataMapper): Mapper<NewNetwork, NewDTO>
    //endregion

    //region local Mappers
    @Binds
    abstract fun bindsNewLocalDataMapper(mapper: NewLocalDataMapper): Mapper<NewLocal, NewDTO>
    //endregion

    //region Presentation Mappers
    @Binds
    abstract fun bindsNewDomainUiMapper(mapper: NewDomainUiMapper): Mapper<NewEntity, NewUiModel>
    //endregion
}