package reqres.rest

import reqres.model.CreateDTO
import reqres.model.GetAllDTO
import reqres.model.GetByIdDTO
import reqres.model.User
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

}