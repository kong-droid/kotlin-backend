package coffee.kotlin.backend.domain.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class RegisterMemoRequest (
    @field:NotBlank
    @field:Schema(required = true, description = "작성자", example = "작성자")
    val name: String,
    @field:NotBlank
    @field:Pattern(regexp = "^[0-9]{6}")
    @field:Schema(required = true, description = "비밀번호", example = "1234")
    val password: String,
    @field:NotBlank
    @field:Schema(required = true, description = "내용", example = "내영이 어떤 부분인지 입력해보세요.")
    val contents: String
)