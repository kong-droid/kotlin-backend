package coffee.kotlin.backend.domain.entity

import coffee.kotlin.backend.domain.entity.common.ImmutableUuidEntity
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "memo")
data class MemoEntity (
    @Id
    override val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val name: String,
    val password: String,
    val contents: String,
    override val createdAt: Instant
): ImmutableUuidEntity(createdAt, id)