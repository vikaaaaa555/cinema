package com.example.cinemaapp.data.room.repository

import androidx.lifecycle.LiveData
import com.example.cinemaapp.models.movies.MovieItemModel

interface MoviesRepository {
    val allMovies: LiveData<List<MovieItemModel>>

    suspend fun insertMovie(movieItemModel: MovieItemModel, onSuccsess:() -> Unit)
    suspend fun deleteMovie(movieItemModel: MovieItemModel, onSuccsess:() -> Unit)
}