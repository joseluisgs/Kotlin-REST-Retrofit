package placeholder.rest

import placeholder.model.User
import retrofit2.Response
import retrofit2.http.GET

/**
 * Voy a usar funciones suspendidas, para poder usar código asíncrono. Si no quitar el suspend y cambiar Response<T>
 * por Call<T>
 * Aqí van todas las llamadas a la API REST que quiera hacer
 */
interface PlaceHolderRest {
    @GET("/users")
    suspend fun getAll(): Response<List<User>>

}