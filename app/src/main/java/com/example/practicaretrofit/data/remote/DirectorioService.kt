
package com.example.practicaretrofit.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface DirectorioService {

    @POST("directorio_buscar")
    suspend fun buscar(
        @Body cuerpo: BuscarRequest
    ): RespuestaApi<UsuarioDto>
}
