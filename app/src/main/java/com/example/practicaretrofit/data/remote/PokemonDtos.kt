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
data class TypeSlotDto(
    val slot: Int,
    val type: NamedResourceDto
)

// GET https://pokeapi.co/api/v2/pokemon/pikachu
data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int, // decimetros
    val weight: Int, // hectogramos
    val types: List<TypeSlotDto>,
    val stats: List<StatSlotDto>
)

data class StatSlotDto(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: com.example.practicaretrofit.data.remote.NamedResourceDto
)

data class NamedResourceDto(
    val name: String,
    val url: String
)
