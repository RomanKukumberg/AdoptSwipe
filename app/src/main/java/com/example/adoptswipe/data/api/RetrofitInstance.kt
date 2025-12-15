package com.example.adoptswipe.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    val api: AnimalApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://meowfacts.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AnimalApi::class.java)
    }
}

