package com.example.cinemaapp.data.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.models.MovieItemModel
import com.google.firebase.firestore.FirebaseFirestore

class firebaseRepository {

    private val db = FirebaseFirestore.getInstance()
    private val moviesFavoriteCollection = db.collection("movies")
    private val favoriteMovies = MutableLiveData<List<MovieItemModel>>()

    init {
        moviesFavoriteCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                // handle error
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val movies = mutableListOf<MovieItemModel>()
                for (document in snapshot.documents) {
                    val movie = document.toObject(MovieItemModel::class.java)
                    movie?.let {
                        movies.add(it)
                    }
                }
                favoriteMovies.value = movies
            }
        }
    }

    fun insert(movie: MovieItemModel) {
        moviesFavoriteCollection.document(movie.id.toString()).set(movie)
    }

    fun delete(movie: MovieItemModel) {
        moviesFavoriteCollection.document(movie.id.toString()).delete()
    }

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return favoriteMovies
    }
}
