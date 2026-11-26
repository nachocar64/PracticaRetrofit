package com.example.practicaretrofit.data.remote

import com.example.practicaretrofit.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FabricaRetrofit {
    private val clienteHttp: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS) // tiempo maximo para conectarse
            .readTimeout(15, TimeUnit.SECONDS) // tiempo maximo esperando la respuesta
            .writeTimeout(15, TimeUnit.SECONDS) // tiempo maximo enviando el cuerpo
        if (BuildConfig.DEBUG) {
            // Solo en desarrollo: en la app publicada no se debe registrar el contenido de las
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BODY
            })
        }
        builder.build()
    }

    /** `baseUrl` debe terminar en "/". */
    fun crear(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
