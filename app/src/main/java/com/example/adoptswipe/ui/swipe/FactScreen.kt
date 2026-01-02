package com.example.adoptswipe.ui.swipe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.adoptswipe.viewmodel.AnimalViewModel

@Composable
fun FactScreen(
    viewModel: AnimalViewModel,
    onNavigate: (String) -> Unit
) {
    val fact by viewModel.fact.collectAsState()
    var isLoading by remember { mutableStateOf(true) }

    // Načítanie prvého faktu
    LaunchedEffect(Unit) {
        viewModel.loadFact()
        isLoading = false
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Hlavný obsah
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = fact,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Button pre nový fakt
        Button(
            onClick = { viewModel.loadFact() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(50.dp)
        ) {
            Text("New Fact")
        }

        // Fixný spodný navigačný bar
        BottomNavigationBar(currentScreen = "swipe", onNavigate = onNavigate)

    }
}
