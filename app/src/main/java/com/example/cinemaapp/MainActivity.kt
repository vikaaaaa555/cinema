package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cinemaapp.databinding.ActivityMainBinding
import com.example.cinemaapp.screens.main.MainMenuProvider


class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding?= null
    private val binding get() = mBinding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MAIN = this
        navController = Navigation.findNavController(this, R.id.nav_host)

        // Создаем новый экземпляр класса MyMenuProvider
        val menuProvider = MainMenuProvider()
        // Добавляем его в качестве провайдера меню
        addMenuProvider(menuProvider)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}