
package com.example.practicaretrofit.data.remote

import com.google.gson.annotations.SerializedName

data class BuscarRequest(
    val apikey: String,
    val q: String,
    val area: String,
    val limit: String,
    val offset: String
)

data class RespuestaApi<T>(
    val data: List<T>
)

data class UsuarioDto(
    val id: Int,
    @SerializedName("nombre_completo")
    val nombreCompleto: String,
    val cargo: String?,
    @SerializedName("area_codigo")
    val areaCodigo: String?,
    val contactos: String?
)

data class ContactoDto(
    val tipo: String,
    val valor: String,
    val principal: Int
)
