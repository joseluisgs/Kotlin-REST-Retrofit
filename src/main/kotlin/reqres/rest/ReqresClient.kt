package reqres.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente de nuestra API REST
 */
object ReqresClient {
    private const val API_URL = "https://reqres.in/"

    // Creamos una instancia de Retrofit con las llamadas a la API
    fun getInstance(): ReqresRest {
        return Retrofit.Builder().baseUrl(API_URL)
            // Nuestro conversor de JSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReqresRest::class.java)
    }
}