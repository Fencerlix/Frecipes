package com.example.frecipes.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromIngredientList(value: List<Ingredient>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIngredientList(value: String): List<Ingredient> {
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromInstructionList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toInstructionList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}