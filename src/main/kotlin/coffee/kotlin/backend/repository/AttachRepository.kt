package coffee.kotlin.backend.repository

import coffee.kotlin.backend.domain.entity.AttachEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AttachRepository: JpaRepository<AttachEntity, UUID> {
    fun findAllByGroupIdOrderByCreatedAtAsc(groupId: UUID): List<AttachEntity>
    fun findFirstByGroupId(groupId: UUID): Optional<AttachEntity>
    @Query("delete from #{#entityName} as a where a.id= :id")
    fun deleteWithId(id: UUID): Long
    fun deleteAllByGroupId(groupId: UUID): Long
}