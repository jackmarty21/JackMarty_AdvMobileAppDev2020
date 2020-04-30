package com.example.lab6.data

import com.example.lab6.data.database.favorite.Favorite
import com.example.lab6.data.database.ingredient.Ingredient
import com.example.lab6.data.database.instruction.Instruction
import com.squareup.moshi.JsonClass
import java.util.*

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
) {

    fun getRoomFavorite(): Favorite {
        return Favorite(
            idDrink,
            strDrink,
            strDrinkThumb,
            strInstructions,
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strMeasure1,
            strMeasure2,
            strMeasure3,
            strMeasure4,
            Date()
        )
    }

    fun getRoomIngredients(): List<Ingredient> {
        var roomIngredientList= mutableListOf<Ingredient>()

        roomIngredientList.add(Ingredient(
            cocktail_id = idDrink,
            name = strIngredient1,
            amount = strMeasure1
        ))
        roomIngredientList.add(Ingredient(
            cocktail_id = idDrink,
            name = strIngredient2,
            amount = strMeasure2
        ))
        roomIngredientList.add(Ingredient(
            cocktail_id = idDrink,
            name = strIngredient3,
            amount = strMeasure3
        ))
        roomIngredientList.add(Ingredient(
            cocktail_id = idDrink,
            name = strIngredient4,
            amount = strMeasure4
        ))

        return roomIngredientList
    }

    fun getRoomInstructions(): Instruction {
        return Instruction(
            cocktail_id = idDrink,
            steps = strInstructions
        )
    }

    companion object {
        fun fromRoomFavorites(fav: Favorite): Cocktail {
            return Cocktail(
                fav.cocktail_id,
                fav.strDrink,
                fav.strDrinkThumb,
                fav.strInstructions,
                fav.strIngredient1,
                fav.strIngredient2,
                fav.strIngredient3,
                fav.strIngredient4,
                fav.strMeasure1,
                fav.strMeasure2,
                fav.strMeasure3,
                fav.strMeasure4
            )
        }

        fun fromRoomTypes(fav: Favorite,
                          roomInstructions: Instruction,
                          roomIngredients: List<Ingredient>): Cocktail {

            var ingredient1 = roomIngredients[0].name
            var ingredient2 = roomIngredients[1].name
            var ingredient3 = roomIngredients[2].name
            var ingredient4 = roomIngredients[3].name

            var amount1 = roomIngredients[0].amount
            var amount2 = roomIngredients[1].amount
            var amount3 = roomIngredients[2].amount
            var amount4 = roomIngredients[3].amount

            var instruction = roomInstructions.steps

            return Cocktail(
                fav.cocktail_id,
                fav.strDrink,
                fav.strDrinkThumb,
                instruction,
                ingredient1,
                ingredient2,
                ingredient3,
                ingredient4,
                amount1,
                amount2,
                amount3,
                amount4
            )
        }
    }
}