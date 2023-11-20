package coffee.kotlin.backend.domain.request

import java.util.UUID

data class RemoveMemoRequest (
    val memoId: UUID,
    val password: String
)