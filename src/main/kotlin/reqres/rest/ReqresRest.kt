package reqres.rest

import reqres.model.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Voy a usar funciones suspendidas, para poder usar código asíncrono. Si no quitar el suspend y cambiar Response<T>
 * por Call<T>
 * Aqí van todas las llamadas a la API REST que quiera hacer
 */
interface ReqresRest {

    @GET("api/users")
    suspend fun getAll(@Query("page") page: Int = 0, @Query("per_page") perPage: Int = 0): Response<GetAllDTO>

    @GET("api/users/{id}")
    suspend fun getById(@Path("id") id: Int): Response<GetByIdDTO>

    @POST("api/users")
    suspend fun create(@Body user: User): Response<CreateDTO>

    @PUT("api/users/{id}")
    suspend fun update(@Path("id") id: Int, @Body user: User): Response<UpdateDTO>

    @PATCH("api/users/{id}")
    suspend fun upgrade(@Path("id") id: Int, @Body user: User): Response<UpdateDTO>

    @DELETE("api/users/{id}")
    suspend fun delete(@Path("id") id: Int): Response<Void> // Es void porque no devuelve nada

}
