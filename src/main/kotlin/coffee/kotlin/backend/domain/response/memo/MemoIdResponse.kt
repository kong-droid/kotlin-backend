package coffee.kotlin.backend.domain.response.memo

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class MemoIdResponse (

    @field:Schema(required = true, description = "메모 인덱스", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    val memoId: UUID?
)