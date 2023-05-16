package com.example.cinemaapp.screens.detail

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cinemaapp.BASE_URL
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.POSTER_PATH
import com.example.cinemaapp.R
import com.example.cinemaapp.data.firebase.MoviesRepository
import com.example.cinemaapp.databinding.FragmentDetailBinding
import com.example.cinemaapp.models.MovieItemModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailFragment : Fragment() {

    private var mBinding: FragmentDetailBinding?= null
    private val binding get() = mBinding!!
    private lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            currentMovie = arguments?.getSerializable("movie", MovieItemModel::class.java) as MovieItemModel
        }

        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("favorites")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newFavoriteDocument = FirebaseFirestore.getInstance()
            .collection("favorites").document()
        init(newFavoriteDocument)
    }

    private fun init(newFavoriteDocument: DocumentReference) {
        Glide.with(MAIN)
            .load("$POSTER_PATH${currentMovie.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageDetail)
        binding.tvTitle.text = currentMovie.title
        binding.tvDate.text = currentMovie.release_date
        binding.tvDescrition.text = currentMovie.overview

        val favoritesCollection = Firebase.firestore.collection("favorites")

        binding.imageDetailFavorite.setOnClickListener {
            isFavorite = if (!isFavorite) {
                binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_24)

                // Создаем новый документ в коллекции и записываем туда данные фильма
                favoritesCollection.document(currentMovie.id.toString())
                    .set(currentMovie)
                    .addOnSuccessListener {
                        Toast.makeText(
                            this.requireContext(),
                            "${currentMovie.title} added to favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this.requireContext(),
                            "Error adding ${currentMovie.title} to favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                true
            } else {
                binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_border_24)

                // Удаляем документ с данными фильма из коллекции
                favoritesCollection.document(currentMovie.id.toString())
                    .delete()
                .addOnSuccessListener {
                        Toast.makeText(
                            this.requireContext(),
                            "${currentMovie.title} removed from favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this.requireContext(),
                            "Error removing ${currentMovie.title} from favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}