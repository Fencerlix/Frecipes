package com.example.frecipes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "recipes")
@TypeConverters(RecipeConverters::class)
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val instructions: List<String>
) {
    init {
        require(servings in 1..20) { "Die Anzahl der Portionen muss zwischen 1 und 20 liegen." }
    }
}

data class Ingredient(
    val amount: Float,
    val unit: String,
    val name: String
) {
    init {
        require(amount > 0 && amount < 10000 || unit.isEmpty()) { "Die Menge muss zwischen 0 und 10.000 liegen." }
        require(unit.length <= 10 || unit.isEmpty()) { "Die Einheit darf maximal 10 Zeichen enthalten." }
        require(name.length <= 30) { "Der Name darf maximal 30 Zeichen enthalten." }
    }
}