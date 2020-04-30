package com.example.lab6.data.database.relation

import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.lab6.data.database.favorite.Favorite
import com.example.lab6.data.database.ingredient.Ingredient
import com.example.lab6.data.database.instruction.Instruction

data class FavoriteWithDetails (
    @Embedded @PrimaryKey val favorite: Favorite,

    @Relation(
        parentColumn = "cocktail_id",
        entityColumn = "cocktail_id",
        entity = Instruction::class
    ) val instruction: List<Instruction>,

    @Relation(
        parentColumn = "cocktail_id",
        entityColumn = "cocktail_id",
        entity = Ingredient::class
    ) val ingredients: List<Ingredient>
)