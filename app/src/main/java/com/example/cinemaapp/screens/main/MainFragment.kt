package com.example.cinemaapp.screens.main

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.R
import com.example.cinemaapp.databinding.FragmentMainBinding
import com.example.cinemaapp.models.MovieItemModel

class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding ?= null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Скрываем название приложения в ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)

        // Устанавливаем макет с поиском в ActionBar
        val searchLayout = layoutInflater.inflate(R.layout.search_layout, null)

        // Получаем ссылку на ActionBar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar

        // Устанавливаем пользовательское представление только для фрагмента MainFragment
        actionBar?.let {
            if (it.customView == null) {
                it.setCustomView(searchLayout)
            }
        }


        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.initDatabase()
        recyclerView = binding.recycleviewMain
        recyclerView.adapter = adapter
        try {
            viewModel.getMoviesRetrofit()
            viewModel.myMovies.observe(viewLifecycleOwner) { list ->  //возможно на строчке снизу нужно не viewLifecycleOwner, а this
                adapter.setList(list.body()!!.results)
            }
        } catch (e: Exception) {
            Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object {
        fun clickMovie(model: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }


}