package com.example.adoptswipe.ui.swipe

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.adoptswipe.viewmodel.AnimalViewModel

@Composable
fun SwipeScreen(viewModel: AnimalViewModel) {

    val fact by viewModel.fact.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFact()
    }

    Surface {
        Text(
            text = fact,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
