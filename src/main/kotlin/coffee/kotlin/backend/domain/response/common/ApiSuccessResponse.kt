package coffee.kotlin.backend.domain.response.common

data class ApiSuccessResponse<T>(override val code: Int, override val message: String,val data:T): ApiResponse(code, message)