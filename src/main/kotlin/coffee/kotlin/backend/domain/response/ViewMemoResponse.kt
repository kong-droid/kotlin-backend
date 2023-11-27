package coffee.kotlin.backend.domain.response

import coffee.kotlin.backend.domain.entity.MemoEntity
import java.time.Instant
import java.util.UUID

data class ViewMemoResponse (
    val memoId: UUID,
    val name: String,
    val contents: String,
    val createdAt: Instant
) {
    constructor(entity: MemoEntity): this(entity.id, entity.name, entity.contents, entity.createdAt)
}