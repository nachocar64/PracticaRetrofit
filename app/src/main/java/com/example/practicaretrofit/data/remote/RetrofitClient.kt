package com.example.practicaretrofit.data.remote

object RetrofitClient {
    // Debe terminar en "/" para que Retrofit pueda concatenar las rutas relativas.
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokeApiService by lazy {
        FabricaRetrofit.crear(BASE_URL).create(PokeApiService:: class. java)

    }
}