package com.example.lab6.data.database.ingredient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IngredientDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredients_table WHERE cocktail_id = :id")
    fun getIngredientsForRecipe(id: Int): List<Ingredient>
}