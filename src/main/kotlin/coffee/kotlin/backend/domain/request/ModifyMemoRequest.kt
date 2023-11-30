package coffee.kotlin.backend.domain.request

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class ModifyMemoRequest (
    @field:NotNull
    @field:Schema(required = true, description = "메모 인덱스", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    val memoId: UUID,
    @field:NotBlank
    @field:Pattern(regexp = "^[0-9]{6}")
    @field:Schema(required = true, description = "비밀번호", example = "1234")
    val password: String,
    @field:NotBlank
    @field:Schema(required = true, description = "내용", example = "내영이 어떤 부분인지 입력해보세요.")
    val contents: String
)