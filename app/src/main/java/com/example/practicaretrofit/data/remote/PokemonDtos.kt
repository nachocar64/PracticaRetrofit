package com.example.practicaretrofit.data.remote

import com.google.gson.annotations.SerializedName

// GET https://pokeapi.co/api/v2/pokemon?limit=20&offset=0
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    // puede ser null: por eso lleva "?"
    val results: List<PokemonItemDto>
)
data class PokemonItemDto(
    val name: String,
    val url: String
)