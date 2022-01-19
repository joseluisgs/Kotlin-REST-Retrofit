package placeholder.rest

import placeholder.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Voy a usar funciones suspendidas, para poder usar código asíncrono. Si no quitar el suspend y cambiar Response<T>
 * por Call<T>
 * Aqí van todas las llamadas a la API REST que quiera hacer
 */
interface PlaceHolderRest {

    @GET("/users")
    suspend fun getAll(): Response<List<User>>

    @GET("/users/{id}")
    suspend fun getById(@Path("id") id: Int): Response<User>

    @POST("/users")
    suspend fun create(@Body user: User): Response<User>

}