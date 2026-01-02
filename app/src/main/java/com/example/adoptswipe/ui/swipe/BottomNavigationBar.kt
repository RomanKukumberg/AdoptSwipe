package com.example.adoptswipe.ui.swipe

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    currentScreen: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == "swipe",
            onClick = { onNavigate("swipe") },
            label = { Text("Swipe") },
            icon = {}
        )
        NavigationBarItem(
            selected = currentScreen == "favourites",
            onClick = { onNavigate("favourites") },
            label = { Text("Favourites") },
            icon = {}
        )
        NavigationBarItem(
            selected = currentScreen == "facts",
            onClick = { onNavigate("facts") },
            label = { Text("Facts") },
            icon = {}
        )
    }
}
