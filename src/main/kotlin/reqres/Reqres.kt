package reqres

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import reqres.dto.LoginDTO
import reqres.model.User
import reqres.rest.ReqresClient

object Reqres {
    private val restClient = ReqresClient.getInstance()
    private lateinit var TOKEN: String

    suspend fun run() = coroutineScope {
        println()
        println("------------------------------------------------------")
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
        val delete = launch(Dispatchers.IO) { delete(4) }
        create.join()
        update.join()
        upgrade.join()
        delete.join()

        // Si lo hacemos con flujos podemos jugar con operaciones
        // https://kotlinlang.org/docs/flow.html#flow-context
        // Los flujos son asíncronos, por lo que no necesitamos hacer nada de corritinas, es
        val flowAll = getAllFlow().collect { println(it?.toJSON()) }
        val flowAllSimple = getAllFlowSimple()
            .filter { it?.first_name?.contains("a") == true }
            .take(3)
            .collect { println(it?.toJSON()) }
        val flowById = getByIdFlow(3).collect { println(it?.toJSON()) }

        // Las async/await se usa realmente cuando queremos obtener valores deuna función asíncrona, por eso difire del lucch, el cual no devolvería nada
        // usando corrutinas: https://www.geeksforgeeks.org/launch-vs-async-in-kotlin-coroutines/
        // Vamos a simular un login y que nuestra api para alguna operaciones necesita un token
        // Login
        val login = async(Dispatchers.IO) { login(LoginDTO("eve.holt@reqres.in", "cityslicka")) }
        TOKEN = login.await().toString()
        if (TOKEN.isNotEmpty()) {
            println("Login correcto con token: $TOKEN")
        } else {
            println("Login incorrecto")
        }
        val flowToken = getAllWithToken()
            .filter { it?.first_name?.contains("a") == true }
            .take(3)
            .collect { println(it?.toJSON()) }
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
            println(data?.toJSON())
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

    /**
     * Voy a hacerlo con un flujo de datos
     */
    suspend fun getAllFlow(): Flow<User?> {
        println("GET /users -> getAll as Flow")

        return flow {
            // get the comment Data from the api
            val response = restClient.getAll(1, 10)
            if (response.isSuccessful) {
                val data = response.body()?.data
                data?.forEach {
                    emit(it)
                }
            } else {
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Voy a hacerlo con un flujo de datos simplificado
     */
    suspend fun getAllFlowSimple(): Flow<User?> {
        println("GET /users -> getAll as Flow Simple")
        return restClient.getAll(1, 10).body()?.data?.asFlow() ?: flowOf(null).flowOn(Dispatchers.IO)
    }

    private suspend fun getByIdFlow(id: Int = 5): Flow<User?> {
        println("GET /users/id -> getById Flow")
        return flow {
            val response = restClient.getById(id)
            if (response.isSuccessful) {
                emit(response.body()?.data)
            } else {
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Realiza un login
     * @param DTO de Login
     * @return Token
     */
    private suspend fun login(login: LoginDTO): String? {
        println("POST /login -> login")
        val response = restClient.login(login)
        if (response.isSuccessful) {
            println(response.body().toJSON())
            return response.body()?.token
        } else {
            println("Error: ${response.code()}")
        }
        return null
    }

    /**
     * Get all users con Token del tipo Bearer
     * @param page page number
     * @param perPage number of users per page
     */
    private suspend fun getAllWithToken(page: Int = 1, perPage: Int = 10): Flow<User?> {
        println("GET /users with Token -> getAll flow with token")
        return restClient.getAllWithToken(token = "Bearer ${this.TOKEN}", 1, 10)
            .body()?.data?.asFlow() ?: flowOf(null).flowOn(Dispatchers.IO)
    }

}

// Me creo funciones de extensión para poder usar el Gson. Lo ideal sería mapear el modelo, usando los resultados para
private fun Any?.toJSON() = GsonBuilder().setPrettyPrinting().create().toJson(this)