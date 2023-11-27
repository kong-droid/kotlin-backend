package coffee.kotlin.backend.domain.response

import coffee.kotlin.backend.domain.entity.MemoEntity
import java.util.UUID

data class MemoIdResponse (
    val memoId: UUID
) {
    constructor(entity: MemoEntity): this(entity.id);
}