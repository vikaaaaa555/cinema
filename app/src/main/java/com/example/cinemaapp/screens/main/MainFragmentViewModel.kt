package com.example.cinemaapp.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemaapp.REALIZATION
import com.example.cinemaapp.data.retrofit.RetrofitRepository
import com.example.cinemaapp.data.room.MoviesRoomDatabase
import com.example.cinemaapp.data.room.repository.MoviesRepositoryRealization
import com.example.cinemaapp.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application

    fun getMoviesRetrofit() {
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun initDatabase() {
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)
    }
}