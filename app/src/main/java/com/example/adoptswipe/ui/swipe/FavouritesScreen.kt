package com.example.adoptswipe.ui.swipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.adoptswipe.viewmodel.AnimalViewModel

@Composable
fun FavouritesScreen(
    viewModel: AnimalViewModel,
    onNavigate: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.favourites) { animal ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Column {
                        Image(
                            painter = painterResource(id = animal.imageRes),
                            contentDescription = animal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(animal.name, style = MaterialTheme.typography.titleMedium)
                        Text(animal.breed)
                        Text(animal.contact)
                        Spacer(Modifier.height(4.dp))
                        Button(onClick = { viewModel.removeFromFavourites(animal) }) {
                            Text("🗑️ Delete")
                        }
                    }
                }
            }
        }

        BottomNavigationBar(currentScreen = "favourites", onNavigate = onNavigate)
    }
}
