package com.example.rickandmorty.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.viewmodels.RickAndMortyViewModel

class RickAndMortyViewModelFactory(private val repository: CharactersRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RickAndMortyViewModel::class.java)) {
            return RickAndMortyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}