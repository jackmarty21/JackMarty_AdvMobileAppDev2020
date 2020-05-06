package com.example.lab6.data.database.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorites_table")
data class Favorite (
    @PrimaryKey val cocktail_id: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strInstructions: String,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val date_added: Date
)