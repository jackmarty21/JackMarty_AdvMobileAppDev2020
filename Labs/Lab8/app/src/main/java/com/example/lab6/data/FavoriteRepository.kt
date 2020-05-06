package com.example.lab6.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lab6.LOG_TAG
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoriteRepository(val app: Application) {
    private val db = Firebase.firestore

    val favoriteList = MutableLiveData<List<Cocktail>>()
    val favoriteCocktail = MutableLiveData<Cocktail>()
    private val allData: MutableMap<String, Cocktail> = mutableMapOf()

    val cocktailIsFavorite = MutableLiveData<Boolean>()

    init {
        db.collection("favorites").addSnapshotListener { snapshot, e ->
            if(e != null) {
                Log.i(LOG_TAG, "Firebase listen failed", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                parseAllData(snapshot)
            } else {
                Log.e(LOG_TAG, "data is null")
            }
        }
    }

    private fun parseAllData(result: QuerySnapshot) {
        var allFavorites = mutableListOf<Cocktail>()

        for (doc in result) {
            val id: String = (doc.getString("id"))!!
            val name: String = doc.getString("strDrink")!!
            val thumb: String = doc.getString("strDrinkThumb")!!
            val instructions: String = doc.getString("strInstructions")!!

            allFavorites.add(Cocktail(
                id,
                name,
                thumb,
                instructions,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ))

            allData[id] = Cocktail(
                id,
                name,
                thumb,
                instructions,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )

            Log.i(LOG_TAG, "allData: $id, $name, $thumb, $instructions")
        }
        Log.i(LOG_TAG, "allData: $allData")
        favoriteList.value = allFavorites
    }

    fun getCocktail(cocktail: Cocktail) {
        favoriteCocktail.value = allData[cocktail.idDrink]
    }

    fun isCocktailFavorite(id: String) {
        cocktailIsFavorite.value = allData.containsKey(id)
    }
}