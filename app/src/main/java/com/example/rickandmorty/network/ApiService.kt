package com.example.rickandmorty.network

import com.example.rickandmorty.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") pageNo: Int) : Response<CharacterResponse>

}