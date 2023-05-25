package com.example.cinemaapp.data.retrofit

import com.example.cinemaapp.API_KEY
import com.example.cinemaapp.YOUTUBE_API_KEY
import com.example.cinemaapp.data.retrofit.api.RetrofitInstance
import com.example.cinemaapp.models.movies.MoviesModel
import com.example.cinemaapp.models.trailers.TrailerModel
import com.example.cinemaapp.models.youtube.YouTubeVideoDetailsResponse
import retrofit2.Response

class RetrofitRepository {
    suspend fun getPopularMovies(): Response<MoviesModel> {
        return RetrofitInstance.api.getPopularMovies(API_KEY, "en-US", 1)
    }

    suspend fun getMovieTrailers(movieId: Int, API_KEY: String, language: String): Response<TrailerModel> {
        return RetrofitInstance.api.getMovieTrailers(movieId, com.example.cinemaapp.API_KEY, "en-US")
    }

    suspend fun getVideoDetails(videoId: String, apiKey: String): YouTubeVideoDetailsResponse {
        return RetrofitInstance.youTubeApi.getVideoDetails(videoId, "contentDetails", YOUTUBE_API_KEY)
    }
}