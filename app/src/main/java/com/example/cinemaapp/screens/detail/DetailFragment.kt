package com.example.cinemaapp.screens.detail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cinemaapp.*
import com.example.cinemaapp.data.retrofit.RetrofitRepository
import com.example.cinemaapp.databinding.FragmentDetailBinding
import com.example.cinemaapp.models.movies.MovieItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private val viewModel: DetailFragmentViewModel by viewModels()
    private var mBinding: FragmentDetailBinding?= null
    private val binding get() = mBinding!!
    private lateinit var currentMovie: MovieItemModel
    private var isFavorite = false
    private val repository = RetrofitRepository()
    private lateinit var videoViewTrailer: VideoView
    private lateinit var playButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            currentMovie = arguments?.getSerializable("movie", MovieItemModel::class.java) as MovieItemModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)

        videoViewTrailer = view.findViewById(R.id.movie_trailer)
        playButton = view.findViewById(R.id.play_button)

        init()
    }

    private fun init() {
        val valueBool = SaveShared.getFavorite(MAIN, currentMovie.id.toString())
        val viewModel = ViewModelProvider(this)[DetailFragmentViewModel::class.java]

        if (isFavorite != valueBool) {
            binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        binding.tvTitle.text = currentMovie.title
        binding.tvDate.text = currentMovie.release_date
        binding.tvOverride.text = currentMovie.overview
        binding.tvRatingBar.rating = currentMovie.vote_average.toFloat()

        Glide.with(MAIN)
            .load("$POSTER_PATH${currentMovie.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageDetail)

        Glide.with(MAIN)
            .load("$BACKDROP_PATH${currentMovie.backdrop_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.movieBackdrop)

        val movieId = currentMovie.id
        playButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                loadMovieTrailers(movieId)
            }
        }


        binding.imageDetailFavorite.setOnClickListener {
            isFavorite = if (isFavorite == valueBool) {
                binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_24)
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie) {}
                true
            } else {
                binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), false)
                viewModel.delete(currentMovie) {}
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    private suspend fun loadMovieTrailers(movieId: Int) {
        val response = viewModel.getMovieTrailers(movieId)
        if (response.isSuccessful) {
            val youTubeVideoContentDetails = response.body()
            if (youTubeVideoContentDetails != null && youTubeVideoContentDetails.results.isNotEmpty()) {
                val trailer = youTubeVideoContentDetails.results[0]
                val trailerKey = trailer.key
                val site = trailer.site

                val trailerUrl = createTrailerUrl(site, trailerKey)

                playTrailer(trailerUrl)
            } else {
                Log.d("Trailer", "Error, trailer not found")
            }
        } else {
            Log.d("Trailer", "Loading error")
        }
    }

    private fun createTrailerUrl(details: String, videoUrl: String): String {
        return when (details) {
            "YouTube" -> "https://www.youtube.com/watch?v=${videoUrl}"
            else -> ""
        }
    }

    private fun playTrailer(trailerUrl: String) {
        videoViewTrailer.setVideoURI(Uri.parse(trailerUrl))
        videoViewTrailer.start()
    }
}