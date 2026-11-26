package com.example.practicaretrofit.data

import com.example.practicaretrofit.data.remote.PokemonDetailDto
import com.example.practicaretrofit.data.remote.PokemonItemDto
import com.example.practicaretrofit.model.Estadistica
import com.example.practicaretrofit.model.PokemonDetalle
import com.example.practicaretrofit.model.PokemonResumen

private const val URL_IMAGENES =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

/** La API no entrega la imagen en el listado, pero el repositorio de sprites usa el id como nombre. */
private fun urlImagenPorId(id: Int): String = "$URL_IMAGENES$id.png"

fun PokemonDetailDto.aDetalle(): PokemonDetalle = PokemonDetalle(
    id = id,
    nombre = name,
    imagenUrl = urlImagenPorId(id),
    alturaMetros = height / 10.0,
    pesoKilos = weight / 10.0,
    tipos = types.sortedBy { it.slot }.map { it.type.name },
    estadisticas = stats.map { Estadistica(nombre = it.stat.name, valor = it.baseStat) }
)

fun PokemonItemDto.aResumen(): PokemonResumen {
    val id = url.trimEnd('/').substringAfterLast('/').toInt()
    return PokemonResumen(id = id, nombre = name, imagenUrl = com.example.practicaretrofit.data.urlImagenPorId(id))
}
