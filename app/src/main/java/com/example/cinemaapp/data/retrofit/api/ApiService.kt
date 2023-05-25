package com.example.cinemaapp.data.retrofit.api

import com.example.cinemaapp.models.movies.MoviesModel
import com.example.cinemaapp.models.trailers.TrailerModel
import com.example.cinemaapp.models.youtube.YouTubeVideoDetailsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesModel>

    @GET("movie/{movieId}/videos")
    suspend fun getMovieTrailers(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<TrailerModel>

    @GET("videos")
    suspend fun getVideoDetails(
        @Query("id") videoId: String,
        @Query("part") part: String = "contentDetails",
        @Query("key") apiKey: String
    ): YouTubeVideoDetailsResponse
}