
package com.example.practicaretrofit.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.listaDesdeTexto(
    texto: String?
): List<T> {

    if (texto.isNullOrBlank()) {
        return emptyList()
    }

    val tipo = TypeToken.getParameterized(
        List::class.java,
        T::class.java
    ).type

    return fromJson(texto, tipo)
}
