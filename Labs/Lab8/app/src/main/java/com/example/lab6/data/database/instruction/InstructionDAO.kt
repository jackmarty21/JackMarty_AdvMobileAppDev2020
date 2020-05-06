package com.example.lab6.data.database.instruction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InstructionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInstruction(instruction: Instruction)

    @Query("SELECT * FROM instructions_table WHERE cocktail_id = :id")
    fun getInstructionsForRecipe(id: Int): List<Instruction>
}