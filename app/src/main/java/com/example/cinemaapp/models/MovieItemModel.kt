package com.example.cinemaapp.models

import com.google.firebase.firestore.*
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

@IgnoreExtraProperties
data class MovieItemModel(
    @PropertyName("adult") val adult: Boolean,
    @PropertyName("backdrop_path") val backdrop_path: String,
    @PropertyName("genre_ids") val genre_ids: List<Int>,
    @PropertyName("id") val id: Int,
    @PropertyName("original_language") val original_language: String,
    @PropertyName("original_title") val original_title: String,
    @PropertyName("overview") val overview: String,
    @PropertyName("popularity") val popularity: Double,
    @PropertyName("poster_path") val poster_path: String,
    @PropertyName("release_date") val release_date: String,
    @PropertyName("title") val title: String,
    @PropertyName("video") val video: Boolean,
    @PropertyName("vote_average") val vote_average: Double,
    @PropertyName("vote_count") val vote_count: Int
): Serializable {
    @Exclude
    var documentReference: DocumentReference? = null

    constructor() : this(
        false, "", listOf(), 0, "", "", "",
        0.0, "", "", "", false, 0.0, 0
    )

    companion object {
        fun fromSnapshot(snapshot: DocumentSnapshot): MovieItemModel {
            val movie = snapshot.toObject(MovieItemModel::class.java)!!
            movie.documentReference = snapshot.reference
            return movie
        }
    }
}