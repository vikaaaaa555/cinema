package com.example.cinemaapp

import com.example.cinemaapp.data.room.repository.MoviesRepositoryRealization

lateinit var MAIN: MainActivity
val API_KEY = "12a014be11f3867f04241a38e03a2f57"
const val BASE_URL = "https://api.themoviedb.org/"
const val POSTER_PATH = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
lateinit var REALIZATION: MoviesRepositoryRealization