
package com.example.practicaretrofit.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Query

data class NuevaPublicacionRequest(
    val title: String,
    val body: String,
    val userId: Int
)

data class PublicacionDto(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

interface PublicacionesService {

    @GET("posts")
    suspend fun listar(
        @Query("userId") usuarioId: Int
    ): List<PublicacionDto>

    @POST("posts")
    suspend fun crear(
        @Body publicacion: NuevaPublicacionRequest
    ): PublicacionDto

    @PUT("posts/{id}")
    suspend fun reemplazar(
        @Path("id") id: Int,
        @Body publicacion: PublicacionDto
    ): PublicacionDto

    @DELETE("posts/{id}")
    suspend fun eliminar(
        @Path("id") id: Int
    ): Response<Unit>
}

