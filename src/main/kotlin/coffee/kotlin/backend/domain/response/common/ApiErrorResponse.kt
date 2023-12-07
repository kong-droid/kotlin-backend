package coffee.kotlin.backend.domain.response.common

data class ApiErrorResponse(override val code: Int, override val message: String): ApiResponse(code, message)