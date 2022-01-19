package placeholder

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import placeholder.model.Address
import placeholder.model.Company
import placeholder.model.Geo
import placeholder.model.User
import placeholder.rest.PlaceHolderClient


object PlaceHolder {
    private val restClient = PlaceHolderClient.getInstance()

    // Al hacerlo run blocking todo termina cuando todas las funciones suspendidas terminen
    // coroutineScope permite ejecutar una serie de funciones suspendidas
    fun run() = runBlocking {
        println("API REST PlaceHolder - https://jsonplaceholder.typicode.com/")
        println("Resources: Users")
        println("------------------------------------------------------")
        // Lanzo todo en contexto asíncrono y espero a que terminen y en un Dispacher de IO.
        //  Ahora mismo sería asíncrono
        // Y podría lanzar mil cosas de esta manera a la vez
        // Por eso algunas se resolverían antes que otras
        val getAll = async(Dispatchers.IO) { getAll() }
        // Vamos a esperar a que terminen los get para hacer el resto de la ejecución
        // getAll.await() // Si no quieres que se vea mezclado, es asñincrono descomenta esto y comenta la siguiente getAll.await()
        val getById = async(Dispatchers.IO) { getById(3) }
        getAll.await()
        getById.await()
        // Vamos con el POST
        val user = User("Juan", "Perez", "juan@email.com")
        val post = async(Dispatchers.IO) { create(user) }
        // No vamos a actualizar, si modificamos antes, jejeje, aunque no pasa nada, porque el id es otro
        post.await()
        // -Otra vez en paralelo, quien ganara?
        user.name = "Juanito"
        user.address = Address("Calle falsa 123", "Madrid", "Madrid", "España", Geo(40.4, -3.2))
        user.company = Company("Company", "otra", "mas datos")
        val put = async(Dispatchers.IO) { update(3, user) }
        user.name = "Eva"
        val patch = async(Dispatchers.IO) { upgrade(3, user) }
        put.await()
        patch.await()
        var delete = async(Dispatchers.IO) { delete(3) }

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
     * @param id id del usuario
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
     * Crea un nuevo usuario
     * @param user usuario a crear
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

    /**
     * Actualiza un usuario.
     * @param id id del usuario a actualizar
     * @param user usuario con los datos a actualizar
     */
    private suspend fun update(id: Int, user: User) {
        println("PUT /users/id -> update")
        val response = restClient.update(id, user)
        if (response.isSuccessful) {
            println("User updated: ")
            println("User: ${response.body()?.toJSON()}")
        } else {
            println("Error: ${response.code()}")
        }
    }

    /**
     * Actualiza un usuario. Solo algunas cosas
     * @param id id del usuario a actualizar
     * @param user usuario con los datos a actualizar
     */
    private suspend fun upgrade(id: Int, user: User) {
        println("PATCH /users/id -> upgrade")
        val response = restClient.update(id, user)
        if (response.isSuccessful) {
            println("User upgraded: ")
            println("User: ${response.body()?.toJSON()}")
        } else {
            println("Error: ${response.code()}")
        }
    }

    /**
     * Borra un usuario
     * @param id id del usuario a borrar
     */
    private suspend fun delete(id: Int) {
        println("DELETE /users/id -> delete")
        val response = restClient.delete(id)
        if (response.isSuccessful) {
            println("User deleted: ")
            println("User: ${response.body()}") // No va a salir nada!!!
        } else {
            println("Error: ${response.code()}")
        }
    }
}