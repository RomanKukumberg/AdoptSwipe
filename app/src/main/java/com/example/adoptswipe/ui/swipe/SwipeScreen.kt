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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.res.painterResource
import com.example.adoptswipe.data.model.Animal
import com.example.adoptswipe.viewmodel.AnimalViewModel
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        if (animals.isEmpty()) {
            Text("Žiadne mačky 😿", style = MaterialTheme.typography.headlineMedium)
        } else {
            val animal = animals[currentIndex % animals.size] // cyklický index

            var addedToFavourites by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(400.dp)
                    .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (offsetX.value > 200f || offsetX.value < -200f) {
                                    scope.launch {
                                        offsetX.snapTo(0f)
                                        currentIndex++
                                        addedToFavourites = false // reset tlačidla pre ďalšiu mačku
                                    }
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
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = animal.imageRes),
                        contentDescription = animal.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(animal.name, style = MaterialTheme.typography.headlineSmall)
                    Text(animal.breed)
                    Text(animal.description)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            viewModel.addToFavourites(animal)
                            addedToFavourites = true
                        },
                        enabled = !addedToFavourites
                    ) {
                        Text("❤️")
                    }
                }
            }
        }
    }

    // Bottom navigation
    NavigationBar {
        NavigationBarItem(
            selected = true,
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
            selected = false,
            onClick = { onNavigate("facts") },
            label = { Text("Facts") },
            icon = {}
        )
    }
}
