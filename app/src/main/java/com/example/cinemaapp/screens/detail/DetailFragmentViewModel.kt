package com.example.cinemaapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaapp.API_KEY
import com.example.cinemaapp.REALIZATION
import com.example.cinemaapp.YOUTUBE_API_KEY
import com.example.cinemaapp.data.retrofit.RetrofitRepository
import com.example.cinemaapp.models.movies.MovieItemModel
import com.example.cinemaapp.models.trailers.TrailerModel
import com.example.cinemaapp.models.youtube.YouTubeVideoDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DetailFragmentViewModel: ViewModel() {
    private val repository = RetrofitRepository()

    fun insert(movieItemModel: MovieItemModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            REALIZATION.insertMovie(movieItemModel) {
                onSuccess()
            }
        }

    fun delete(movieItemModel: MovieItemModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItemModel) {
                onSuccess()
            }
        }

    suspend fun getMovieTrailers(movieId: Int): Response<TrailerModel> {
        return withContext(viewModelScope.coroutineContext) {
            repository.getMovieTrailers(movieId, API_KEY, "en-US")
        }
    }

    suspend fun getVideoDetails(videoId: String): YouTubeVideoDetailsResponse {
        val apiKey = YOUTUBE_API_KEY
        return repository.getVideoDetails(videoId, apiKey)
    }
}