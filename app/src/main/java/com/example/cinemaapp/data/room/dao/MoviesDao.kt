package com.example.cinemaapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemaapp.models.movies.MovieItemModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItemModel: MovieItemModel)

    @Delete
    suspend fun delete(movieItemModel: MovieItemModel)

    @Query("SELECT * from movie_table")
    fun getAllMovies() : LiveData<List<MovieItemModel>>
}