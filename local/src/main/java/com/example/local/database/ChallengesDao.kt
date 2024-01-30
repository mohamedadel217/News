package com.example.local.database

import androidx.room.*
import com.example.local.model.NewLocal

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(news: List<NewLocal>): List<Long>

    @Query("select * from News")
    fun getAllNews(): List<NewLocal>

    @Query("delete from News")
    fun clearNewsCash(): Int

}