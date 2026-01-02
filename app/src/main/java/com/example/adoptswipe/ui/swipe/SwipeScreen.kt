package com.example.adoptswipe.ui.swipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.rememberCoroutineScope
import com.example.adoptswipe.data.model.Animal
import com.example.adoptswipe.viewmodel.AnimalViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeScreen(
    viewModel: AnimalViewModel,
    onNavigate: (String) -> Unit
) {
    val animals = viewModel.demoAnimals
    var currentIndex by remember { mutableStateOf(0) }

    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Hlavná časť - karta
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            if (animals.isEmpty()) {
                Text("Žiadne mačky 😿", style = MaterialTheme.typography.headlineMedium)
            } else {
                // Ak sme na konci, začneme od začiatku
                if (currentIndex >= animals.size) currentIndex = 0
                val animal = animals[currentIndex]

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.85f)
                        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    if (offsetX.value > 200f || offsetX.value < -200f) {
                                        scope.launch { offsetX.snapTo(0f) }
                                        currentIndex++
                                    } else {
                                        scope.launch { offsetX.animateTo(0f, tween(300)) }
                                    }
                                },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    scope.launch { offsetX.snapTo(offsetX.value + dragAmount.x) }
                                }
                            )
                        },
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Obrázok na hornú časť
                        Image(
                            painter = painterResource(id = animal.imageRes),
                            contentDescription = animal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.6f)
                                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        )

                        Spacer(Modifier.height(8.dp))

                        // Textová časť pod obrázkom
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(animal.name, style = MaterialTheme.typography.headlineSmall)
                            Text(animal.breed, style = MaterialTheme.typography.bodyMedium)
                            Text(animal.description, style = MaterialTheme.typography.bodySmall)
                            Spacer(Modifier.height(8.dp))

                            // ❤️ tlačidlo iba ak mačka ešte nie je vo favourites
                            if (animal !in viewModel.favourites) {
                                Button(
                                    onClick = { viewModel.addToFavourites(animal) },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("❤️")
                                }
                            }
                        }
                    }
                }
            }
        }

        // Fixný BottomNavigation na spodku
        BottomNavigationBar(currentScreen = "swipe", onNavigate = onNavigate)
    }
}
