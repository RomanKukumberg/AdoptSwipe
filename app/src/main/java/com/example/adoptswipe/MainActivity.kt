package com.example.adoptswipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adoptswipe.data.api.RetrofitInstance
import com.example.adoptswipe.data.db.AnimalDatabase
import com.example.adoptswipe.data.repository.AnimalRepository
import com.example.adoptswipe.ui.swipe.FactScreen
import com.example.adoptswipe.ui.swipe.FavouritesScreen
import com.example.adoptswipe.ui.swipe.SwipeScreen
import com.example.adoptswipe.ui.theme.AdoptSwipeTheme
import com.example.adoptswipe.viewmodel.AnimalViewModel
import com.example.adoptswipe.viewmodel.AnimalViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1️⃣ Room databáza
        val database = AnimalDatabase.getDatabase(this)
        val dao = database.animalDao()

        // 2️⃣ Repository (API + DB)
        val repository = AnimalRepository(
            api = RetrofitInstance.api,
            dao = dao
        )

        // 3️⃣ ViewModel Factory
        val factory = AnimalViewModelFactory(repository)

        setContent {
            AdoptSwipeTheme {

                val viewModel: AnimalViewModel = viewModel(factory = factory)

                // Stav pre aktuálnu obrazovku
                var currentScreen by remember { mutableStateOf("swipe") }

                when (currentScreen) {
                    "swipe" -> SwipeScreen(viewModel = viewModel, onNavigate = { screen -> currentScreen = screen })
                    "favourites" -> FavouritesScreen(viewModel = viewModel, onNavigate = { screen -> currentScreen = screen })
                    "facts" -> FactScreen(viewModel = viewModel, onNavigate = { screen -> currentScreen = screen })
                }
            }
        }
    }
}
