package com.example.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.converters.CustomTypesConverter
import com.example.local.model.NewLocal

@Database(
    entities = [NewLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CustomTypesConverter::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
