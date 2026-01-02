package com.example.adoptswipe.data.db

import androidx.room.*
import com.example.adoptswipe.data.model.Animal
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal)

    @Delete
    suspend fun deleteAnimal(animal: Animal)

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): Flow<List<Animal>>
}
