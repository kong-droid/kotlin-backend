package coffee.kotlin.backend.domain.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class ViewMemoRequest (
    @field:Schema(required = true, description = "메모 작성자", example = "작성자")
    val name: String,
    @field:Schema(required = false, description = "메모 등록일")
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val createdAt: LocalDate?
)