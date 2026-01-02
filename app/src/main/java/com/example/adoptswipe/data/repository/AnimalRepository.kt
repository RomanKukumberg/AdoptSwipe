package com.example.adoptswipe.data.repository

import com.example.adoptswipe.data.api.AnimalApi
import com.example.adoptswipe.data.db.AnimalDao
import com.example.adoptswipe.data.model.Animal

class AnimalRepository(
    private val api: AnimalApi,
    private val dao: AnimalDao
) {

    suspend fun getRandomFact(): String {
        val response = api.getFact()
        return response.data.firstOrNull() ?: "No fact available"
    }

    suspend fun saveAnimal(animal: Animal) {
        dao.insertAnimal(animal)
    }

    suspend fun removeAnimal(animal: Animal) {
        dao.deleteAnimal(animal)
    }

    fun getFavourites() = dao.getAllAnimals()
}
