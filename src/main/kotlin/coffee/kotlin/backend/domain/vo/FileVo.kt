package coffee.kotlin.backend.domain.vo

data class FileVo(
    val originName: String?,
    val targetName: String,
    val size: Int,
    val path: String,
    val type: String,
)
