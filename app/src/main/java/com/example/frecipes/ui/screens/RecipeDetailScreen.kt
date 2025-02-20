package com.example.frecipes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frecipes.data.RecipeViewModel
import com.example.frecipes.ui.theme.FrecipesTheme

@Composable
fun RecipeDetailScreen(recipeName: String, viewModel: RecipeViewModel, navController: NavController) {
    val recipe = viewModel.getRecipeByName(recipeName)

    if (recipe != null) {
        FrecipesTheme {
            Column(modifier = Modifier.padding(16.dp)) {
                // Rezeptname
                Text(text = "Rezept: ${recipe.name}")

                // Zutaten
                Text(text = "Zutaten:")
                recipe.ingredients.forEach { ingredient ->
                    Text("${ingredient.amount} ${ingredient.unit} ${ingredient.name}")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Anleitung
                Text(text = "Anleitung:")
                recipe.instructions.forEach { step ->
                    Text(step)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons für Bearbeiten und Löschen
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Bearbeiten-Button
                    Button(
                        onClick = {
                            // Navigiere zur Bearbeitungsseite des Rezepts
                            navController.navigate("edit_recipe/${recipe.name}")
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Bearbeiten")
                    }

                    // Löschen-Button
                    Button(
                        onClick = {
                            // Lösche das Rezept
                            viewModel.deleteRecipe(recipe)
                            // Navigiere zurück zur Rezeptliste
                            navController.popBackStack()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Löschen")
                    }
                }
            }
        }
    } else {
        Text(text = "Rezept nicht gefunden.")
    }
}
