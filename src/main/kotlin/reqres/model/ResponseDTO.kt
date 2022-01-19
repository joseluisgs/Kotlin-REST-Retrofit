package reqres.model


class ResponseDTO {
    var page = 0
    var per_page = 0
    var total = 0
    var total_pages = 0
    var data: ArrayList<User>? = null
    var support: Support? = null
}

class Support {
    var url: String? = null
    var text: String? = null
}

