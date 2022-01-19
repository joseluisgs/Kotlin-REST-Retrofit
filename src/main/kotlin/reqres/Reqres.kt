package reqres

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import reqres.model.User
import reqres.rest.ReqresClient

object Reqres {
    private val restClient = ReqresClient.getInstance()

    suspend fun run() = coroutineScope {
        println("API REST Reqres.in - https://reqres.in/")
        println("Resources: Users")
        println("------------------------------------------------------")
        // ahora en una detrás de otra pero en peticiones distintas con hilos distintos
        val getAll = launch(Dispatchers.IO) { getAll(1, 5) }
        val getById = launch(Dispatchers.IO) { getById(3) }
        // Sincronizamos con el join
        getAll.join()
        getById.join()
        val user = User(
            first_name = "George",
            last_name = "Bluth"
        )
        val create = launch(Dispatchers.IO) { create(user) }
        user.first_name = "Janet"
        user.avatar = "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"
        val update = launch(Dispatchers.IO) { update(2, user) }
        user.email = "pepe@mmail.com"
        val upgrade = launch(Dispatchers.IO) { upgrade(4, user) }
    }

    /**
     * Get all users
     * @param page page number
     * @param perPage number of users per page
     */
    private suspend fun getAll(page: Int = 1, perPage: Int = 10) {
        println("GET /users -> getAll")
        val response = restClient.getAll(page, perPage)
        if (response.isSuccessful) {
            // Me quedo con los datos: data
            val data = response.body()?.data
            println("Total users: ${data?.size}")
            // Si el body no es nulo ? imprimo
            data?.forEach {
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
            val data = response.body()?.data
            println("User: ${data?.toJSON()}")
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
            println("User: ${response.body()}")
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
            println("User: ${response.body()}")
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
            println("User: ${response.body()}")
        } else {
            println("Error: ${response.code()}")
        }
    }

}