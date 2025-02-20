package com.example.frecipes.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.frecipes.ui.components.RecipeCard
import com.example.frecipes.data.RecipeViewModel

@Composable
fun RecipeListScreen(viewModel: RecipeViewModel, navController: NavController) {
    // Hole Rezepte aus dem ViewModel
    val recipes by viewModel.getRecipesLiveData().observeAsState(emptyList())

    LazyColumn {
        items(recipes) { recipe ->
            RecipeCard(recipe = recipe, navController = navController)
        }
    }
}
