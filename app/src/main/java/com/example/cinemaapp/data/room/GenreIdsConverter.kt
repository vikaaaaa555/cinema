package com.example.cinemaapp.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsConverter {
    @TypeConverter
    fun fromList(genreIds: List<Int>): String {
        // Преобразование списка в строку, например, с использованием JSON
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toList(genreIdsString: String): List<Int> {
        // Преобразование строки обратно в список, например, с использованием JSON
        return Gson().fromJson(genreIdsString, object : TypeToken<List<Int>>() {}.type)
    }
}
