package com.example.practicaretrofit.data.remote

object RetrofitClient {
    // Debe terminar en "/" para que Retrofit pueda concatenar las rutas relativas.
    private const val BASE_URL_POKE = "https://pokeapi.co/api/v2/"

    private const val BASE_URL_JSON = "https://jsonplaceholder.typicode.com/"

    val pokeApi: PokeApiService by lazy {
        FabricaRetrofit.crear(BASE_URL_POKE).create(PokeApiService:: class. java)
    }

    val publiApi: PublicacionesService by lazy {
        FabricaRetrofit.crear(BASE_URL_JSON)
            .create(PublicacionesService::class.java)
    }

}