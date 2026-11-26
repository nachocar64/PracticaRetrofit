package com.example.practicaretrofit.data.remote

import com.example.practicaretrofit.util.Resultado
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException


class PokemonRepository(private val api: PokeApiService = RetrofitClient.api) {

    suspend fun obtenerPokemones(limite: Int, desplazamiento: Int): Resultado<List<PokemonResumen>> =
        try {
            val respuesta = api.obtenerPokemones(limite, desplazamiento)
            Resultado.Exito(respuesta.results.map { it.aResumen() })
        } catch (e: CancellationException) {
            throw e // cancelar la corrutina no es un error
        } catch (e: HttpException) {
            Resultado.Error("El servidor respondió con un error (código ${e.code()}).")
        } catch (e: IOException) {
            Resultado. Error("No hay conexion a internet. Revisa tu red e intentalo de nuevo.")
        } catch (e: Exception) {
            Resultado.Error("Ocurrió un error inesperado.")

        }

}