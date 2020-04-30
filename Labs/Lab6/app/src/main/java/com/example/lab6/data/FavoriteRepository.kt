package com.example.lab6.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lab6.LOG_TAG
import com.example.lab6.data.database.AppDatabase
import com.example.lab6.data.database.Converters
import com.example.lab6.data.database.favorite.Favorite
import com.example.lab6.data.database.ingredient.Ingredient
import com.example.lab6.data.database.instruction.Instruction
import com.example.lab6.data.database.relation.FavoriteWithDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FavoriteRepository(val app: Application) {
    private val db = AppDatabase.getDatabase(app)

    //dao references
    private val favoriteWithDetailsDAO = db.favoriteWithDetailsDAO()
    private val favoriteDAO = db.favoriteDAO()
    private val ingredientDAO = db.ingredientDAO()
    private val instructionDAO = db.instructionDAO()

    val favoriteRoomList: LiveData<List<Favorite>> = favoriteDAO.getAllFavorites()

    fun addFavorite(cocktail: Cocktail) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDAO.insertFavorite(cocktail.getRoomFavorite())

            instructionDAO.insertInstruction(cocktail.getRoomInstructions())

            for (ingredient in cocktail.getRoomIngredients()) {
                ingredientDAO.insertIngredient(ingredient)
            }
        }
    }
    val cocktailList: MutableLiveData<Cocktail> = MutableLiveData()

    fun getCocktail(cocktail: Cocktail) {
        val fav = favoriteDAO.getFavorite(cocktail.idDrink.toInt())
        Log.d(LOG_TAG, "here")
        cocktailList.postValue(Cocktail.fromRoomFavorites(fav))
    }

}