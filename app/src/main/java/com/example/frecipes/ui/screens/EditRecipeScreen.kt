package com.example.frecipes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frecipes.data.Ingredient
import com.example.frecipes.data.RecipeViewModel
import com.example.frecipes.ui.theme.FrecipesTheme

@Composable
fun EditRecipeScreen(recipeName: String, viewModel: RecipeViewModel, navController: NavController) {
    // Hole das Rezept aus dem ViewModel
    val recipe = viewModel.getRecipeByName(recipeName)

    if (recipe == null) {
        Text(text = "Rezept nicht gefunden.")
        return
    }

    // Zustand für die bearbeiteten Felder
    var editedName by remember { mutableStateOf(recipe.name) }
    var editedServings by remember { mutableStateOf(recipe.servings) }
    var editedIngredients by remember { mutableStateOf(recipe.ingredients.toMutableList()) }
    var editedInstructions by remember { mutableStateOf(recipe.instructions.toMutableList()) }

    // UI für das Bearbeiten des Rezepts
    FrecipesTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Bearbeite Rezept: ${recipe.name}")

            // Name bearbeiten
            OutlinedTextField(
                value = editedName,
                onValueChange = { editedName = it },
                label = { Text("Name des Rezepts") },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            // Anzahl der Portionen bearbeiten
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Portionen: ")
                IconButton(onClick = { if (editedServings > 1) editedServings-- }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Portion verringern")
                }
                Text(text = "$editedServings", modifier = Modifier.padding(horizontal = 8.dp))
                IconButton(onClick = { if (editedServings < 20) editedServings++ }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Portion erhöhen")
                }
            }

            // Zutaten bearbeiten
            Text(text = "Zutaten", modifier = Modifier.padding(top = 16.dp))
            editedIngredients.forEachIndexed { index, ingredient ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                    OutlinedTextField(
                        value = ingredient.amount.toString(),
                        onValueChange = { amount ->
                            val updatedIngredients = editedIngredients.toMutableList()
                            updatedIngredients[index] = ingredient.copy(amount = amount.toFloatOrNull() ?: ingredient.amount)
                            editedIngredients = updatedIngredients
                        },
                        label = { Text("Menge") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = ingredient.unit,
                        onValueChange = { unit ->
                            val updatedIngredients = editedIngredients.toMutableList()
                            updatedIngredients[index] = ingredient.copy(unit = unit)
                            editedIngredients = updatedIngredients
                        },
                        label = { Text("Einheit") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = ingredient.name,
                        onValueChange = { name ->
                            val updatedIngredients = editedIngredients.toMutableList()
                            updatedIngredients[index] = ingredient.copy(name = name)
                            editedIngredients = updatedIngredients
                        },
                        label = { Text("Zutat") },
                        modifier = Modifier.weight(2f)
                    )
                    IconButton(onClick = {
                        editedIngredients = editedIngredients.toMutableList().apply { removeAt(index) }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Zutat löschen", tint = Color.Red)
                    }
                }
            }

            // Zutat hinzufügen
            Button(
                onClick = { editedIngredients.add(Ingredient(0f, "", "")) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Zutat hinzufügen")
                Text("Zutat hinzufügen")
            }

            // Anleitung bearbeiten
            Text(text = "Anleitung", modifier = Modifier.padding(top = 16.dp))
            editedInstructions.forEachIndexed { index, step ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                    OutlinedTextField(
                        value = step,
                        onValueChange = { newStep ->
                            val updatedInstructions = editedInstructions.toMutableList()
                            updatedInstructions[index] = newStep
                            editedInstructions = updatedInstructions
                        },
                        label = { Text("Schritt ${index + 1}") },
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        editedInstructions = editedInstructions.toMutableList().apply { removeAt(index) }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Schritt löschen", tint = Color.Red)
                    }
                }
            }

            // Rezeptschritt hinzufügen
            Button(
                onClick = { editedInstructions.add("") },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Schritt hinzufügen")
                Text("Schritt hinzufügen")
            }

            // Button zum Speichern
            Button(
                onClick = {
                    val updatedRecipe = recipe.copy(
                        name = editedName,
                        servings = editedServings,
                        ingredients = editedIngredients,
                        instructions = editedInstructions
                    )
                    viewModel.addRecipe(updatedRecipe) // Rezept aktualisieren
                    navController.popBackStack() // Zurück zur Rezeptliste
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Speichern")
            }
        }
    }
}
