package coffee.kotlin.backend.repository

import coffee.kotlin.backend.domain.entity.MemoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.util.UUID

interface MemoRepository: JpaRepository<MemoEntity, UUID> {
    fun findAllByName(name: String, pageable: Pageable): Page<MemoEntity>
    fun findAllByNameAndCreatedAtIsBetween(name: String, startAt: Instant, endAt: Instant,
                                           pageable: Pageable): Page<MemoEntity>
    fun deleteByIdAndPassword(memoId: UUID, password: String): Long
}
