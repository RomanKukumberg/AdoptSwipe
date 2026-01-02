package com.example.adoptswipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptswipe.data.model.Animal
import com.example.adoptswipe.data.repository.AnimalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import com.example.adoptswipe.R

class AnimalViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    // Fakty z API
    private val _fact = MutableStateFlow("Loading...")
    val fact = _fact.asStateFlow()

    fun loadFact() {
        viewModelScope.launch {
            _fact.value = repository.getRandomFact()
        }
    }

    val demoAnimals = listOf(
        Animal(
            id = 1,
            name = "Mimi",
            age = "1 year",
            breed = "Domestic Short Hair",
            imageRes = R.drawable.mackaks1,
            description = "Playful kitty who loves cuddles and scratches 😸"
        ),
        Animal(
            id = 2,
            name = "Lili",
            age = "2 years",
            breed = "Siamese",
            imageRes = R.drawable.mackasiam2,
            description = "Lili is gentle and calm, perfect for families with children."
        ),
        Animal(
            id = 3,
            name = "Oscar",
            age = "3 years",
            breed = "Persian",
            imageRes = R.drawable.mackapers3,
            description = "Oscar is elegant and enjoys sleeping in cozy places."
        )
    )

    // Favourites
    val favourites = mutableStateListOf<Animal>()

    fun addToFavourites(animal: Animal) {
        if (!favourites.contains(animal)) {
            favourites.add(animal)
        }
    }

    fun removeFromFavourites(animal: Animal) {
        favourites.remove(animal)
    }
}
