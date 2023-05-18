package com.example.cinemaapp.data.room.repository

import androidx.lifecycle.LiveData
import com.example.cinemaapp.data.room.dao.MoviesDao
import com.example.cinemaapp.models.MovieItemModel

class MoviesRepositoryRealization(private val moviesDao: MoviesDao): MoviesRepository {
    override val allMovies: LiveData<List<MovieItemModel>>
        get() = moviesDao.getAllMovies()

    override suspend fun insertMovie(movieItemModel: MovieItemModel, onSuccsess: () -> Unit) {
        moviesDao.insert(movieItemModel)
        onSuccsess()
    }

    override suspend fun deleteMovie(movieItemModel: MovieItemModel, onSuccsess: () -> Unit) {
        moviesDao.delete(movieItemModel)
        onSuccsess()
    }
}