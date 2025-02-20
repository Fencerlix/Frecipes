package com.example.frecipes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frecipes.data.Recipe

@Composable
fun RecipeCard(recipe: Recipe, navController: NavController) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
        // Navigiere zur Rezeptdetailansicht und übergebe das Rezept
        navController.navigate("recipe_detail/${recipe.name}")
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${recipe.name}")
        }
    }
}
