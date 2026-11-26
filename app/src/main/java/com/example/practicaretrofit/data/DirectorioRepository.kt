
package com.example.practicaretrofit.data

import com.example.practicaretrofit.BuildConfig
import com.example.practicaretrofit.data.remote.BuscarRequest
import com.example.practicaretrofit.data.remote.RetrofitClient
import com.example.practicaretrofit.data.remote.DirectorioService
import com.example.practicaretrofit.data.remote.UsuarioDto
import com.example.practicaretrofit.util.Resultado
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException

class DirectorioRepository(
    private val api: DirectorioService =
        RetrofitClient.dirApi
) {

    private fun cuerpoDeBusqueda(
        texto: String,
        area: String = "",
        pagina: Int = 0
    ): BuscarRequest =
        BuscarRequest(
            apikey = BuildConfig.API_KEY,
            q = texto,
            area = area,
            limit = "20",
            offset = (pagina * 20).toString()
        )

    suspend fun buscar(
        texto: String,
        area: String = "",
        pagina: Int = 0
    ): Resultado<List<UsuarioDto>> =
        try {
            val cuerpo = cuerpoDeBusqueda(
                texto, area, pagina
            )

            val respuesta = api.buscar(cuerpo)

            Resultado.Exito(respuesta.data)

        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Resultado.Error("Error del servidor: ${e.code()}")
        } catch (e: IOException) {
            Resultado.Error("Error de conexión a internet.")
        } catch (e: Exception) {
            Resultado.Error("Ocurrió un error inesperado.")
        }
}
