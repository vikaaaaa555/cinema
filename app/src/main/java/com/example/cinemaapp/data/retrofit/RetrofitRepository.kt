package com.example.cinemaapp.data.retrofit

import com.example.cinemaapp.data.retrofit.api.RetrofitInstance
import com.example.cinemaapp.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel> {
        return RetrofitInstance.api.getPopularMovies()
    }
}