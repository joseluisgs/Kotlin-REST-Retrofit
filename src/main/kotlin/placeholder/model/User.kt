package placeholder.model

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
)


