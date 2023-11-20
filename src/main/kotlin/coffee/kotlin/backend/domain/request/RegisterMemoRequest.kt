package coffee.kotlin.backend.domain.request

data class RegisterMemoRequest (
    val name: String,
    val password: String,
    val contents: String
)