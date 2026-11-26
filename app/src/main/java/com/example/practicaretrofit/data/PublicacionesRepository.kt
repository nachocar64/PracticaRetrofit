
package com.example.practicaretrofit.data

import com.example.practicaretrofit.data.remote.NuevaPublicacionRequest
import com.example.practicaretrofit.data.remote.PublicacionDto
import com.example.practicaretrofit.data.remote.RetrofitClient
import com.example.practicaretrofit.data.remote.PublicacionesService
import com.example.practicaretrofit.util.Resultado
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException

class PublicacionesRepository(
    private val api: PublicacionesService =
        RetrofitClient.publiApi
) {

    private suspend fun <T> ejecutar(
        bloque: suspend () -> T
    ): Resultado<T> =
        try {
            Resultado.Exito(bloque())
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Resultado.Error("Error del servidor: ${e.code()}")
        } catch (e: IOException) {
            Resultado.Error("Error de conexión a internet.")
        } catch (e: Exception) {
            Resultado.Error("Ocurrió un error inesperado.")
        }

    suspend fun listar(
        usuarioId: Int
    ): Resultado<List<PublicacionDto>> =
        ejecutar {
            api.listar(usuarioId)
        }

    suspend fun crear(
        publicacion: NuevaPublicacionRequest
    ): Resultado<PublicacionDto> =
        ejecutar {
            api.crear(publicacion)
        }

    suspend fun reemplazar(
        id: Int,
        publicacion: PublicacionDto
    ): Resultado<PublicacionDto> =
        ejecutar {
            api.reemplazar(id, publicacion)
        }

    suspend fun eliminar(
        id: Int
    ): Resultado<Unit> =
        ejecutar {
            val respuesta = api.eliminar(id)

            if (!respuesta.isSuccessful) {
                throw HttpException(respuesta)
            }
        }
}
