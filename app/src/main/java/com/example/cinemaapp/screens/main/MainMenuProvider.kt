package com.example.cinemaapp.screens.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.R

class MainMenuProvider : MenuProvider {

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.item_favorite -> {
                navigateToFavoriteList()
                true
            }
            else -> false
        }
    }

    private fun navigateToFavoriteList() {
        val currentFragment = MAIN.navController.currentDestination?.id
        when (currentFragment) {
            R.id.mainFragment -> MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
            R.id.detailFragment -> MAIN.navController.navigate(R.id.action_detailFragment_to_favoriteFragment)
        }
    }
}
