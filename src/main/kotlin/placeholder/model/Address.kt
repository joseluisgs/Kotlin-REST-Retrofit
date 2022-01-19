package placeholder.model

/**
 * Dirección de los usuarios
 */
data class Address(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    var geo: Geo
)
