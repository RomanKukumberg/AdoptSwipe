package com.example.adoptswipe.ui.swipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.adoptswipe.viewmodel.AnimalViewModel

@Composable
fun FavouritesScreen(
    viewModel: AnimalViewModel,
    onNavigate: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.favourites) { animal ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = animal.imageRes,
                            contentDescription = animal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(animal.name, style = MaterialTheme.typography.titleMedium)
                        Text(animal.breed)
                    }
                }
            }
        }

        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { onNavigate("swipe") },
                label = { Text("Swipe") },
                icon = {}
            )
            NavigationBarItem(
                selected = true,
                onClick = { onNavigate("favourites") },
                label = { Text("Favourites") },
                icon = {}
            )
            NavigationBarItem(
                selected = false,
                onClick = { onNavigate("facts") },
                label = { Text("Facts") },
                icon = {}
            )
        }
    }
}
