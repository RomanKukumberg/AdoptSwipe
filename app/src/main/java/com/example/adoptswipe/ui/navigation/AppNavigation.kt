package com.example.adoptswipe.ui.navigation

import androidx.compose.runtime.*
import com.example.adoptswipe.ui.swipe.FactScreen
import com.example.adoptswipe.ui.swipe.FavouritesScreen
import com.example.adoptswipe.ui.swipe.SwipeScreen
import com.example.adoptswipe.viewmodel.AnimalViewModel

@Composable
fun AppNavigation(viewModel: AnimalViewModel) {
    var currentScreen by remember { mutableStateOf("swipe") }

    when (currentScreen) {
        "swipe" -> SwipeScreen(viewModel) { currentScreen = it }
        "favourites" -> FavouritesScreen(viewModel) { currentScreen = it }
        "facts" -> FactScreen(viewModel) { currentScreen = it }
    }
}
