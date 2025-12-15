package com.example.adoptswipe.data.api

import com.example.adoptswipe.data.model.MeowFactResponse
import retrofit2.http.GET

interface AnimalApi {

    @GET("/")
    suspend fun getFact(): MeowFactResponse
}
