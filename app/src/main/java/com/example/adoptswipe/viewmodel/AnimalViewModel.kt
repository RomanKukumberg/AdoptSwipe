package com.example.adoptswipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptswipe.data.repository.AnimalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _fact = MutableStateFlow("Loading...")
    val fact = _fact.asStateFlow()

    fun loadFact() {
        viewModelScope.launch {
            _fact.value = repository.getRandomFact()
        }
    }
}
