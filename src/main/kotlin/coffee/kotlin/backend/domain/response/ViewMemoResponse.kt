package coffee.kotlin.backend.domain.response

import coffee.kotlin.backend.domain.entity.MemoEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import java.util.UUID

data class ViewMemoResponse (
    @field:Schema(required = true, description = "메모 인덱스", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    val memoId: UUID,
    @field:Schema(required = true, description = "작성자", example = "작성자")
    val name: String,
    @field:Schema(required = true, description = "내용", example = "메모 내용입니다.")
    val contents: String,
    @field:Schema(required = true, description = "작성일")
    val createdAt: Instant
) {
    constructor(entity: MemoEntity): this(entity.id, entity.name, entity.contents, entity.createdAt)
}