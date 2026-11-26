
package com.example.practicaretrofit

import com.example.practicaretrofit.data.remote.FabricaRetrofit
import com.example.practicaretrofit.data.remote.NuevaPublicacionRequest
import com.example.practicaretrofit.data.remote.PublicacionesService
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class PublicacionesTest {

    private lateinit var servidor: MockWebServer
    private lateinit var api: PublicacionesService

    @Before
    fun preparar() {
        servidor = MockWebServer()
        servidor.start()

        api = FabricaRetrofit
            .crear(servidor.url("/").toString())
            .create(PublicacionesService::class.java)
    }

    @After
    fun cerrar() {
        servidor.shutdown()
    }

    @Test
    fun comprobarPost() = runBlocking {
        servidor.enqueue(
            MockResponse()
                .setResponseCode(201)
                .addHeader("Content-Type", "application/json")
                .setBody(
                    """{"id":101,"title":"Hola","body":"Prueba","userId":1}"""
                )
        )

        val publicacion = NuevaPublicacionRequest(
            title = "Hola",
            body = "Prueba",
            userId = 1
        )

        val creada = api.crear(publicacion)
        val peticion = servidor.takeRequest()

        assertEquals("POST", peticion.method)
        assertEquals("/posts", peticion.path)

        val json = JsonParser().parse(
            peticion.body.readUtf8()
        ).asJsonObject

        assertEquals("Hola", json.get("title").asString)
        assertEquals("Prueba", json.get("body").asString)
        assertEquals(1, json.get("userId").asInt)

        assertEquals(101, creada.id)
    }
}
