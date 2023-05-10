package com.example.cinemaapp.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.example.cinemaapp.R

class MyMenuProvider : MenuProvider {
    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Обработка выбранного пункта меню
        return true
    }
}
/*
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создаем новый экземпляр класса MyMenuProvider
        val menuProvider = MyMenuProvider()

        // Добавляем его в качестве провайдера меню
        addMenuProvider(menuProvider)
    }
}*/
