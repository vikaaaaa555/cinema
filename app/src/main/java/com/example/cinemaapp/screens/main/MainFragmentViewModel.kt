package com.example.cinemaapp.screens.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.data.retrofit.RetrofitRepository
import com.example.cinemaapp.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel: ViewModel() {
    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                //Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}