package com.example.practicaretrofit.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    /** Lista paginada: /pokemon?limit=20&offset=0 */
    @GET("pokemon")
    suspend fun obtenerPokemones(
        @Query("limit") limite: Int,
        @Query("offset") desplazamiento: Int
    ): PokemonListResponse


    /** Detalle: /pokemon/pikachu */

    @GET("pokemon/{nombre}")
    suspend fun obtenerPokemon(
        @Path("nombre") nombre: String
    ): PokemonDetailDto

}