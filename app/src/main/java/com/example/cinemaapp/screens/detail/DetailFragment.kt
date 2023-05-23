package com.example.cinemaapp.screens.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cinemaapp.*
import com.example.cinemaapp.databinding.FragmentDetailBinding
import com.example.cinemaapp.models.MovieItemModel
import com.example.cinemaapp.screens.favorite.FavoriteFragmentViewModel

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Очищаем пользовательское представление ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.customView = null
        // Показываем название приложения в ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

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
}