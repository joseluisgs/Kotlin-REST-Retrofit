package placeholder.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente de nuestra API REST
 */
object PlaceHolderClient {
    private const val API_URL = "https://jsonplaceholder.typicode.com/"

    // Creamos una instancia de Retrofit con las llamadas a la API
    fun getInstance(): PlaceHolderRest {
        return Retrofit.Builder().baseUrl(API_URL)
            // Nuestro conversor de JSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaceHolderRest::class.java)
    }
}