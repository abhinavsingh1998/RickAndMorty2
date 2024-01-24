package com.example.rickandmorty.repository


import com.example.rickandmorty.models.CharacterResponse
import com.example.rickandmorty.network.ApiService
import retrofit2.Response


class CharactersRepository (private val apiService: ApiService) {


    suspend fun getAllCharactersByPageNo(pageNo: Int): Response<CharacterResponse> {
        return apiService.getAllCharacters(pageNo)
    }
}