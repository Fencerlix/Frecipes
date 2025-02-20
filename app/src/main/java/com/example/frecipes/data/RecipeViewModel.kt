package com.example.frecipes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeDao: RecipeDao) : ViewModel() {

    // Eine LiveData für alle Rezepte
    private val allRecipes = MutableLiveData<List<Recipe>>()

    init {
        getRecipes()  // Beim Start alle Rezepte abrufen
    }

    // Alle Rezepte abrufen
    private fun getRecipes() {
        viewModelScope.launch {
            val recipes = recipeDao.getAllRecipes()
            allRecipes.postValue(recipes)
        }
    }

    // Alle Rezepte abrufen
    fun getRecipesLiveData(): LiveData<List<Recipe>> = allRecipes

    // Rezept hinzufügen
    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.insert(recipe)
            getRecipes()  // Nach dem Hinzufügen die Liste aktualisieren
        }
    }

    // Rezept löschen
    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.delete(recipe)
            getRecipes()  // Nach dem Löschen die Liste aktualisieren
        }
    }

    // Funktion zum Abrufen eines Rezepts nach dem Namen
    fun getRecipeByName(recipeName: String): Recipe? {
        // Suche das Rezept anhand des Namens in der Liste
        return allRecipes.value?.find { it.name == recipeName }
    }
}

