package com.example.cinemaapp.screens.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.POSTER_PATH
import com.example.cinemaapp.R
import com.example.cinemaapp.databinding.FragmentDetailBinding
import com.example.cinemaapp.models.MovieItemModel

class DetailFragment : Fragment() {

    private var mBinding: FragmentDetailBinding?= null
    private val binding get() = mBinding!!
    lateinit var currentMovie: MovieItemModel
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
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[DetailFragmentViewModel::class.java]
        Glide.with(MAIN)
            .load("$POSTER_PATH${currentMovie.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageDetail)
        binding.tvTitle.text = currentMovie.title
        binding.tvDate.text = currentMovie.release_date
        binding.tvDescrition.text = currentMovie.overview

        binding.imageDetailFavorite.setOnClickListener {
            isFavorite = if(!isFavorite) {
                binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_24)

                true
            } else {
                binding.imageDetailFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}