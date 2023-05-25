package com.example.cinemaapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.R
import com.example.cinemaapp.databinding.FragmentFavoriteBinding
import com.example.cinemaapp.models.movies.MovieItemModel

class FavoriteFragment : Fragment() {

    private var mBinding: FragmentFavoriteBinding?= null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Favorites"
        (requireActivity() as AppCompatActivity).supportActionBar?.customView = null
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
        recyclerView = binding.recycleviewFavorite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) { list ->
            adapter.setList(list.asReversed())
        }
    }

    companion object {
        fun clickMovie(model: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }
}