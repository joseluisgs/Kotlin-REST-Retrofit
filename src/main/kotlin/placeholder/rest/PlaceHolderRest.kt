package placeholder.rest

import placeholder.model.User
import retrofit2.Response
import retrofit2.http.*

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

    @PUT("/users/{id}")
    suspend fun update(@Path("id") id: Int, @Body user: User): Response<User>

    @PATCH("/users/{id}")
    suspend fun upgrade(@Path("id") id: Int, @Body user: User): Response<User>

    @DELETE("/users/{id}")
    suspend fun delete(@Path("id") id: Int): Response<Void> // Es void porque no devuelve nada

}