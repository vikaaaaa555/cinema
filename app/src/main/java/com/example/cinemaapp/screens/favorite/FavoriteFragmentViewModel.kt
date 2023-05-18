package com.example.cinemaapp.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cinemaapp.REALIZATION
import com.example.cinemaapp.data.room.repository.MoviesRepositoryRealization
import com.example.cinemaapp.models.MovieItemModel

class FavoriteFragmentViewModel: ViewModel() {
    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return REALIZATION.allMovies
    }
}