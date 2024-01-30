package com.example.local.converters

import androidx.room.TypeConverter
import com.example.local.model.SourceLocal
import com.google.gson.Gson

class CustomTypesConverter {

    //SourceLocal converter
    @TypeConverter
    fun sourceLocalToString(sourceLocal: SourceLocal): String = Gson().toJson(sourceLocal)

    @TypeConverter
    fun stringToSourceLocal(string: String): SourceLocal = Gson().fromJson(string, SourceLocal::class.java)
}