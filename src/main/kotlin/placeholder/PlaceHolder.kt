package placeholder

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import placeholder.model.User
import placeholder.rest.PlaceHolderClient

object PlaceHolder {
    val restClient = PlaceHolderClient.getInstance()

    // Al hacerlo run blocking todo termina cuando todas las funciones suspendidas terminen
    fun run() = runBlocking {
        println("API REST PlaceHolder - Users")
        println("-------------------------------------")
        // Lanzo todo en contexto asíncrono y espero a que terminen y en un Dispacher de IO.
        //  Ahora mismo sería asíncrono
        // Y podría lanzar mil cosas de esta manera a la vez
        // Por eso algunas se resolverían antes que otras
        var getAll = async(Dispatchers.Default) { getAll() }
        // Vamos a esperar a que terminen los get para hacer el resto de la ejecución
        // getAll.await() // Si no quieres que se vea mezclado, es asñincrono descomenta esto y comenta la siguiente getAll.await()
        var getById = async(Dispatchers.Default) { getById(3) }
        getAll.await()
        getById.await()
        // Vamos con el POST
        var user = User("Juan", "Perez", "juan@email.com")
        var post = async(Dispatchers.Default) { create(user) }
        // No vamos a actualizar, si modificamos antes, jejeje, aunque no pasa nada, porque el id es otro
        post.await()
    }

    /**
     * Obtiene todos los usuarios
     */
    private suspend fun getAll() {
        println("GET /users -> getAll")
        val response = restClient.getAll()
        if (response.isSuccessful) {
            println("Total users: ${response.body()?.size}")
            // Si el body no es nulo ? imprimo
            response.body()?.forEach {
                println("User: ${it.toJSON()}")
            }
        } else {
            println("Error: ${response.code()}")
        }
    }

    /**
     * Obtiene un usuario por su id
     */
    private suspend fun getById(id: Int = 5) {
        println("GET /users/id -> getById")
        val response = restClient.getById(id)
        if (response.isSuccessful) {
            println("User by id: ")
            println("User: ${response.body()?.toJSON()}")
        } else {
            println("Error: ${response.code()}")
        }
    }

    /**
     * Obtiene un usuario por su id
     */
    private suspend fun create(user: User) {
        println("POST /users -> create")
        val response = restClient.create(user)
        if (response.isSuccessful) {
            println("User created: ")
            println("User: ${response.body()?.toJSON()}")
        } else {
            println("Error: ${response.code()}")
        }
    }

}