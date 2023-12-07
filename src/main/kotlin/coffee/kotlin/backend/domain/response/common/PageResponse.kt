package coffee.kotlin.backend.domain.response.common

import org.springframework.data.domain.Page

data class PageResponse<T>(val list: List<T>, val totalCount: Long, val totalPages: Int) {
    constructor(page: Page<T>): this(page.content, page.totalElements, page.totalPages)
}