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

    val favoriteCocktailList: MutableLiveData<List<Cocktail>> = MutableLiveData()

    val favList = favRepo.cocktailList

    private val favoriteListObserver = Observer<List<Favorite>> {
        val allCocktails = mutableListOf<Cocktail>()

        for(fav in it) {
            allCocktails.add(Cocktail.fromRoomFavorites(fav))
        }

        favoriteCocktailList.value = allCocktails
    }

    init {
        favRepo.favoriteRoomList.observeForever(favoriteListObserver)
    }

    override fun onCleared() {
        favRepo.favoriteRoomList.removeObserver(favoriteListObserver)
        super.onCleared()
    }

    fun favSelected(cocktail: Cocktail) {
        favRepo.getCocktail(cocktail)
    }

    fun addFavorite(cocktail: Cocktail) {
        Log.i(LOG_TAG, "Adding favorite: ${cocktail}")
        favRepo.addFavorite(cocktail)
    }
}