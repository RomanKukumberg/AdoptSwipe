package com.example.adoptswipe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val age: String,
    val breed: String,
    val imageRes: Int,
    val description: String
)
