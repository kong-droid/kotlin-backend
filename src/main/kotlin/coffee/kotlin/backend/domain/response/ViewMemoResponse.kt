package coffee.kotlin.backend.domain.response

import java.time.LocalDate
import java.util.UUID

data class ViewMemoResponse (
    val memoId: UUID,
    val name: String,
    val contents: String,
    val createdAt: LocalDate
)