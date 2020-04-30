package com.example.lab6.data.database

import android.content.Context
import androidx.room.*
import com.example.lab6.data.database.favorite.Favorite
import com.example.lab6.data.database.favorite.FavoriteDAO
import com.example.lab6.data.database.ingredient.Ingredient
import com.example.lab6.data.database.ingredient.IngredientDAO
import com.example.lab6.data.database.instruction.Instruction
import com.example.lab6.data.database.instruction.InstructionDAO
import com.example.lab6.data.database.relation.FavoriteWithDetailsDAO

@Database(entities = [Ingredient::class, Instruction::class, Favorite::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteWithDetailsDAO(): FavoriteWithDetailsDAO
    abstract fun favoriteDAO(): FavoriteDAO
    abstract fun ingredientDAO(): IngredientDAO
    abstract fun instructionDAO(): InstructionDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            //return instance if it exists
            if(tempInstance != null) return tempInstance

            //create instance if it does not exist
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "cocktail_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}