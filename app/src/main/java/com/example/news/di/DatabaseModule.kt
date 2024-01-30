package com.example.news.di

import dagger.Provides
import android.content.Context
import androidx.room.Room
import com.example.local.database.NewsDataBase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext application: Context): NewsDataBase {
        return Room
            .databaseBuilder(application, NewsDataBase::class.java, "news_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDataBase: NewsDataBase) =
        newsDataBase.newsDao()
}