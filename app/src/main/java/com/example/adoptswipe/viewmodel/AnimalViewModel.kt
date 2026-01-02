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

    // Demo dáta mačiek
    val demoAnimals = listOf(
        Animal(
            id = 1,
            name = "Mimi",
            age = "1 rok",
            breed = "Domáca krátkosrstá",
            imageRes = R.drawable.mackaks1,
            description = "Hravá mačička, ktorá miluje škrabkanie a maznanie 😸"
        ),
        Animal(
            id = 2,
            name = "Lili",
            age = "2 roky",
            breed = "Siamská",
            imageRes = R.drawable.mackasiam2,
            description = "Lili je milá a pokojná mačka, vhodná k deťom."
        ),
        Animal(
            id = 3,
            name = "Oscar",
            age = "3 roky",
            breed = "Perská",
            imageRes = R.drawable.mackapers3,
            description = "Oscar je elegantný a rád spí na pohodlných miestach."
        )
    )

    // Favourites
    val favourites = mutableStateListOf<Animal>()

    fun addToFavourites(animal: Animal) {
        if (!favourites.contains(animal)) {
            favourites.add(animal)
        }
    }
}
