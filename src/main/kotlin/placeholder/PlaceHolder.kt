package placeholder

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import placeholder.rest.PlaceHolderClient

object PlaceHolder {
    val restClient = PlaceHolderClient.getInstance()

    // Al hacerlo run blocking todo termina cuando todas las funciones suspendidas terminen
    fun run() = runBlocking {
        println("API REST PlaceHolder - Users")
        println("-------------------------------------")
        // Lanzo todo en contexto asíncrono y espero a que terminen y en un Dispacher de IO. Ahora mismo sería asíncrono
        // Y podría lanzra mil cosas de esta manera
        // Por eso algunas se resolverían antes que otras
        async(Dispatchers.IO) { getAllUsers() }
    }

    /**
     * Obtiene todos los usuarios
     */
    private suspend fun getAllUsers() {
        println("GET /users -> getAll")
        val response = restClient.getAll()
        if (response.isSuccessful) {
            println("Total users: ${response.body()?.size}")
            println(response.body())
        } else {
            println("Error: ${response.code()}")
        }
    }
}