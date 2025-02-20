package com.example.frecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.frecipes.data.RecipeDatabase
import com.example.frecipes.data.RecipeViewModel
import com.example.frecipes.navigation.NavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datenbank-Instanz abrufen
        val database = RecipeDatabase.getDatabase(applicationContext)
        val recipeDao = database.recipeDao()

        // ViewModel erstellen
        val viewModel = RecipeViewModel(recipeDao)

        setContent {
            val navController = rememberNavController()
            NavGraph(navController, viewModel)
        }
    }
}

