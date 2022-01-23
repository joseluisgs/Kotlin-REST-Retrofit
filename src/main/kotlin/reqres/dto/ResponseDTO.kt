package reqres.model

import java.util.*


data class GetAllDTO(
    var page: Int = 0,
    var per_page: Int = 0,
    var total: Int = 0,
    var total_pages: Int = 0,
    var data: ArrayList<User>?,
    var support: Support?,
)

data class GetByIdDTO(
    var data: User?,
    var support: Support?,
)

data class CreateDTO(
    var id: Int? = 0,
    var first_name: String,
    var last_name: String,
    var avatar: String?,
    var email: String?,
    var createdAt: Date?
)

data class UpdateDTO(
    var id: Int? = 0,
    var first_name: String,
    var last_name: String,
    var avatar: String?,
    var email: String?,
    var updatedAt: Date? = null
)


data class Support(
    var url: String?,
    var text: String?
)

data class TokenDTO(
    var token: String? = null
)


