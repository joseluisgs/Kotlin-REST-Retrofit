package reqres.model

data class User(
    val id: Int = 0,
    var first_name: String,
    var last_name: String,
    var avatar: String?,
    var email: String?,
) {
    constructor(first_name: String, last_name: String) :
            this(0, first_name, last_name, null, null)

}
