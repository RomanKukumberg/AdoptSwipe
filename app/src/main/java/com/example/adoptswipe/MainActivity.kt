package com.example.adoptswipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.adoptswipe.data.api.RetrofitInstance
import com.example.adoptswipe.data.repository.AnimalRepository
import com.example.adoptswipe.ui.swipe.SwipeScreen
import com.example.adoptswipe.ui.theme.AdoptSwipeTheme
import com.example.adoptswipe.viewmodel.AnimalViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = AnimalRepository(RetrofitInstance.api)
        val viewModel = AnimalViewModel(repository)

        setContent {
            AdoptSwipeTheme {
                SwipeScreen(viewModel = viewModel)
            }
        }
    }
}
