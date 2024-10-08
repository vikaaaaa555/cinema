package com.example.cinemaapp.models.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cinemaapp.data.room.GenreIdsConverter
import java.io.Serializable

@Entity(tableName = "movie_table")
@TypeConverters(GenreIdsConverter::class)
data class MovieItemModel(
    @PrimaryKey(autoGenerate = true)
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,

    @ColumnInfo
    val id: Int,
    val original_language: String,
    val original_title: String,

    @ColumnInfo
    val overview: String,
    val popularity: Double,

    @ColumnInfo
    val poster_path: String,

    @ColumnInfo
    val release_date: String,

    @ColumnInfo
    val title: String,
    val video: Boolean,

    @ColumnInfo
    val vote_average: Double,
    val vote_count: Int
): Serializable