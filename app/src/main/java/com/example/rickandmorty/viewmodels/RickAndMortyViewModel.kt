package com.example.rickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.models.CharacterData
import com.example.rickandmorty.models.CharacterResponse
import com.example.rickandmorty.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RickAndMortyViewModel (private val repository: CharactersRepository): ViewModel() {

    private val charactersMutableLiveData = MutableLiveData<CharacterResponse>()
    val charactersResponseLiveData: LiveData<CharacterResponse> = charactersMutableLiveData

    val characterDetailsMutableLiveData = MutableLiveData<CharacterData>()

     fun getAllCharactersByPageNo(pageNo: Int) {
         viewModelScope.launch(Dispatchers.IO) {
             val response = repository.getAllCharactersByPageNo(pageNo)
             if(response.isSuccessful) {
                 charactersMutableLiveData.postValue(response.body())
             } else {
                 //TODO Handle failure case
             }
         }
    }
}