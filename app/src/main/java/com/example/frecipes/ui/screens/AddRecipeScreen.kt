package com.example.frecipes.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.frecipes.data.Ingredient
import com.example.frecipes.data.Recipe
import com.example.frecipes.data.RecipeViewModel
import com.example.frecipes.ui.theme.FrecipesTheme

@Composable
fun AddRecipeScreen(viewModel: RecipeViewModel, navController: NavController) {
    var recipeName by remember { mutableStateOf("") }
    var servings by remember { mutableStateOf("1") }

    val context = LocalContext.current

    var ingredientName by remember { mutableStateOf("") }
    var ingredientAmount by remember { mutableStateOf("") }
    var ingredientUnit by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf<List<Ingredient>>(emptyList()) }

    var instructionStep by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf<List<String>>(emptyList()) }

    FrecipesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Neues Rezept hinzufügen", style = MaterialTheme.typography.headlineSmall)

            // Rezeptname
            OutlinedTextField(
                value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Rezeptname") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // Portionen
            OutlinedTextField(
                value = servings,
                onValueChange = { if (it.toIntOrNull() in 1..20 || it.isEmpty()) servings = it },
                label = { Text("Portionen") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // Zutaten
            Text("Zutaten", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = ingredientName,
                    onValueChange = { if (it.length <= 30 || it.isEmpty()) ingredientName = it },
                    label = { Text("Zutat") },
                    modifier = Modifier.weight(2f).padding(4.dp)
                )
                OutlinedTextField(
                    value = ingredientAmount,
                    onValueChange = { if (it.isEmpty() || it.toFloatOrNull() != null) ingredientAmount = it },
                    label = { Text("Menge (optional)") },
                    modifier = Modifier.weight(1f).padding(4.dp)
                )
                OutlinedTextField(
                    value = ingredientUnit,
                    onValueChange = { if (it.length <= 10 || it.isEmpty()) ingredientUnit = it },
                    label = { Text("Einheit (optional)") },
                    modifier = Modifier.weight(1f).padding(4.dp)
                )
            }
            Button(
                onClick = {
                    val amount = ingredientAmount.toFloatOrNull() ?: 0f
                    if (ingredientName.isNotEmpty()) {
                        ingredients = ingredients + Ingredient(amount, ingredientUnit, ingredientName)
                        ingredientAmount = ""
                        ingredientUnit = ""
                        ingredientName = ""
                    } else {
                        Toast.makeText(context, "Zutat benötigt mindestens einen Namen!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Zutat hinzufügen")
                Text("Zutat hinzufügen", modifier = Modifier.padding(start = 8.dp))
            }

            LazyColumn {
                itemsIndexed(ingredients) { index, ingredient ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        OutlinedTextField(
                            value = ingredient.name,
                            onValueChange = { newValue ->
                                ingredients = ingredients.toMutableList().apply { this[index] = ingredient.copy(name = newValue) }
                            },
                            modifier = Modifier.weight(2f).padding(4.dp),
                            label = { Text("Zutat") }
                        )
                        OutlinedTextField(
                            value = ingredient.amount.toString(),
                            onValueChange = { newValue ->
                                ingredients = ingredients.toMutableList().apply { this[index] = ingredient.copy(amount = newValue.toFloatOrNull() ?: 0f) }
                            },
                            modifier = Modifier.weight(1f).padding(4.dp),
                            label = { Text("Menge") }
                        )
                        OutlinedTextField(
                            value = ingredient.unit,
                            onValueChange = { newValue ->
                                ingredients = ingredients.toMutableList().apply { this[index] = ingredient.copy(unit = newValue) }
                            },
                            modifier = Modifier.weight(1f).padding(4.dp),
                            label = { Text("Einheit") }
                        )
                        IconButton(onClick = { ingredients = ingredients.filterIndexed { i, _ -> i != index } }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Zutat löschen", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }

            // Anleitung
            Text("Anleitung", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = instructionStep,
                    onValueChange = { instructionStep = it },
                    label = { Text("Schritt") },
                    modifier = Modifier.weight(1f).padding(4.dp)
                )
            }
            Button(
                onClick = {
                    if (instructionStep.isNotEmpty()) {
                        instructions = instructions + instructionStep
                        instructionStep = ""
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Schritt hinzufügen")
                Text("Schritt hinzufügen", modifier = Modifier.padding(start = 8.dp))
            }

            LazyColumn {
                itemsIndexed(instructions) { index, step ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        OutlinedTextField(
                            value = step,
                            onValueChange = { newValue ->
                                instructions = instructions.toMutableList().apply { this[index] = newValue }
                            },
                            modifier = Modifier.weight(1f).padding(4.dp),
                            label = { Text("Schritt ${index + 1}") }
                        )
                        IconButton(onClick = { instructions = instructions.filterIndexed { i, _ -> i != index } }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Schritt löschen", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }

            // Rezept speichern
            Button(
                onClick = {
                    if (recipeName.isNotEmpty() && ingredients.isNotEmpty() && instructions.isNotEmpty()) {
                        val newRecipe = Recipe(
                            name = recipeName,
                            servings = servings.toIntOrNull() ?: 1,
                            ingredients = ingredients,
                            instructions = instructions
                        )
                        viewModel.addRecipe(newRecipe)
                        Toast.makeText(context, "Rezept hinzugefügt!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Bitte alle Felder ausfüllen!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Rezept speichern")
            }
        }
    }
}