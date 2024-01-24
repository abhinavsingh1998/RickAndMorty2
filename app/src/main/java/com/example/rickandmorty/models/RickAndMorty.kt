package com.example.rickandmorty.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse (
    @SerializedName("info") val info: ApiInfo?,
    @SerializedName("results") val results: ArrayList<CharacterData>?
)

data class ApiInfo (
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("pages") val pages: Int?,
    @SerializedName("prev") val prev: String?
)

data class CharacterData (
    @SerializedName("created") val created: String?,
    @SerializedName("episode") val episode: ArrayList<String>?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("image") val image: String?,
    @SerializedName("location") val location: Location?,
    @SerializedName("name") val name: String?,
    @SerializedName("origin") val origin: Origin?,
    @SerializedName("species") val species: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?
)

data class Location(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)

data class Origin(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)