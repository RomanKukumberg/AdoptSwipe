package com.example.adoptswipe.data.repository

import com.example.adoptswipe.data.api.AnimalApi

class AnimalRepository(private val api: AnimalApi) {

    suspend fun getRandomFact(): String {
        val response = api.getFact()
        return response.data.firstOrNull() ?: "No fact available"
    }
}
