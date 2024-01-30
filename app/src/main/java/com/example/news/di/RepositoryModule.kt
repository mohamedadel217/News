package com.example.news.di

import com.example.data.repository.LocalDataSource
import com.example.data.repository.NewRepositoryImp
import com.example.data.repository.RemoteDataSource
import com.example.domain.repository.NewRepository
import com.example.local.source.LocalDataSourceImp
import com.example.remote.source.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun provideLocalDataSource(localDataSource: LocalDataSourceImp): LocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun provideNewsRepository(repository: NewRepositoryImp): NewRepository

}