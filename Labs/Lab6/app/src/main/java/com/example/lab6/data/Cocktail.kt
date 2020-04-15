package com.example.lab6.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse (
    val drinks: Set<Cocktail>
)

@JsonClass(generateAdapter = true)
data class Cocktail (
    val idDrink: String,
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
    val strMeasure4: String?
)