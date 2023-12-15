package coffee.kotlin.backend.domain.response.common

abstract class ApiResponse(open val code: Int, open val message: String)