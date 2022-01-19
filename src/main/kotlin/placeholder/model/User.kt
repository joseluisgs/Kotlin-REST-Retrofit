package placeholder.model

import com.google.gson.GsonBuilder


/**
 * Usuario, campos obligatorios y campos opcionales ?. (El ?. es para indicar que el valor puede ser nulo)
 */
data class User(
    val id: Int = 0,
    var name: String,
    var username: String,
    var email: String,
    var address: Address?,
    var phone: String?,
    var website: String?,
    var company: Company?
) {
    // Me hago otro consutructor para no pasar datos en nulo desde el main... y no tener que meterlos todos
    constructor(name: String, username: String, email: String) :
            this(0, name, username, email, null, null, null, null)

    fun toJSON(): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)
    }
}


