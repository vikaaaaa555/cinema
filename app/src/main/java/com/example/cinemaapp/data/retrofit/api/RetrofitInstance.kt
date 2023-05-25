package com.example.cinemaapp.data.retrofit.api

import com.example.cinemaapp.BASE_URL
import com.example.cinemaapp.YOUTUBE_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val youTubeRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val youTubeApi: ApiService by lazy {
        youTubeRetrofit.create(ApiService::class.java)
    }
}