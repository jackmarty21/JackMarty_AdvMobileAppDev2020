package com.example.lab6.ui.favorites

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab6.LOG_TAG
import com.example.lab6.data.Cocktail
import com.example.lab6.data.FavoriteRepository
import com.example.lab6.data.database.favorite.Favorite

class SharedFavoritesViewModel(app: Application) : AndroidViewModel(app) {
    private val favRepo = FavoriteRepository(app)

    val favoriteCocktailList = favRepo.favoriteList

    //val favList = favRepo.cocktailList


    fun favSelected(cocktail: Cocktail) {
        favRepo.getCocktail(cocktail)
    }

    fun addFavorite(cocktail: Cocktail) {

    }

    fun isCocktailFavorite(id: String) {
        favRepo.isCocktailFavorite(id)
    }
}