package com.example.cinemaapp

import com.example.cinemaapp.data.room.repository.MoviesRepositoryRealization

lateinit var MAIN: MainActivity
const val API_KEY = "12a014be11f3867f04241a38e03a2f57"
const val YOUTUBE_API_KEY = "AIzaSyBwQ64MFIdN11nIKOAeDIV8V6cw5gm96gY"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
const val POSTER_PATH = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
const val BACKDROP_PATH = "https://www.themoviedb.org/t/p/w1280"
lateinit var REALIZATION: MoviesRepositoryRealization
lateinit var TITTLE_NAME: String