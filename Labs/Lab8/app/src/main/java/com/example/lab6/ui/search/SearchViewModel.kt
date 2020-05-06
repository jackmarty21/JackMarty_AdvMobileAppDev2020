package com.example.lab6.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab6.data.Cocktail
import com.example.lab6.data.CocktailRepository

class SearchViewModel(app: Application) : AndroidViewModel(app) {

    //instantiate repository class
    private val cocktailRepo = CocktailRepository(app)

    //get reference to LiveData object with a value of type List<Recipe>
    val cocktailData = cocktailRepo.cocktailData

    val cocktailDetails = cocktailRepo.cocktailDetails

    val selectedCocktail = MutableLiveData<Cocktail>()

    val searchUserInput = MutableLiveData<String>()

    //add the recipe repo observer
    init {
        selectedCocktail.observeForever(cocktailRepo.cocktailSelectedObserver)
        searchUserInput.observeForever(cocktailRepo.searchTermEntered)
    }

    //called when the ViewModel is no longer used
    override fun onCleared() {
        //remove observers added with observe forever to prevent memory leak
        selectedCocktail.removeObserver(cocktailRepo.cocktailSelectedObserver)
        searchUserInput.removeObserver(cocktailRepo.searchTermEntered)
        super.onCleared()
    }
}