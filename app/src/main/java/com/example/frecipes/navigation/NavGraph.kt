package com.example.frecipes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.frecipes.data.RecipeViewModel
import com.example.frecipes.ui.screens.*
import androidx.navigation.NavHostController

@Composable
fun NavGraph(navController: NavHostController, viewModel: RecipeViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("recipe_list") { RecipeListScreen(viewModel, navController) }
        composable("recipe_detail/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            RecipeDetailScreen(recipeName, viewModel, navController)
        }
        composable("add_recipe") { AddRecipeScreen(viewModel, navController) }
        composable("edit_recipe/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            EditRecipeScreen(recipeName, viewModel, navController)
        }
    }
}