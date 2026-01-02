package com.example.adoptswipe.ui.swipe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adoptswipe.viewmodel.AnimalViewModel

@Composable
fun FactScreen(
    viewModel: AnimalViewModel,
    onNavigate: (String) -> Unit
) {
    val fact by viewModel.fact.collectAsState()
    LaunchedEffect(Unit) { viewModel.loadFact() }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(fact, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))
        }

        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { onNavigate("swipe") },
                label = { Text("Swipe") },
                icon = {}
            )
            NavigationBarItem(
                selected = false,
                onClick = { onNavigate("favourites") },
                label = { Text("Favourites") },
                icon = {}
            )
            NavigationBarItem(
                selected = true,
                onClick = { onNavigate("facts") },
                label = { Text("Facts") },
                icon = {}
            )
        }
    }
}
