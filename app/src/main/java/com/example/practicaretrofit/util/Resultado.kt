package com.example.practicaretrofit.util

sealed class Resultado<out T> {
    data class Exito<out T>(val datos: T) : Resultado<T>()
    data class Error(val mensaje: String) : Resultado<Nothing>()
}
