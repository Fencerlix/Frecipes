package com.example.frecipes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Startseite mit Buttons
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("recipe_list") }, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Meine Rezepte")
        }
        Button(onClick = { navController.navigate("add_recipe") }, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Neues Rezept")
        }
    }
}
