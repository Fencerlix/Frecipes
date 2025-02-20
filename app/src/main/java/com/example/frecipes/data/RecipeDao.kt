package com.example.frecipes.data

import androidx.room.*

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>
}