package com.example.lab6.data.database.ingredient

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lab6.data.database.favorite.Favorite

@Entity(tableName = "ingredients_table",
    foreignKeys = [ForeignKey(
        entity = Favorite::class,
        parentColumns = ["cocktail_id"],
        childColumns = ["cocktail_id"]
)])
data class Ingredient (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cocktail_id: String,
    val name: String?,
    val amount: String?
)