package coffee.kotlin.backend.domain.request

import java.time.LocalDate

data class ViewMemoRequest (
    val name: String,
    val createdAt: LocalDate? = null
)