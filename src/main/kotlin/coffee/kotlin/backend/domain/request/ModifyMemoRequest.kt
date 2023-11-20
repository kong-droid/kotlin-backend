package coffee.kotlin.backend.domain.request

import java.util.UUID

data class ModifyMemoRequest (
    val memoId: UUID,
    val password: String,
    val contents: String
)