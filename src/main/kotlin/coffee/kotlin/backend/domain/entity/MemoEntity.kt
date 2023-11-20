package coffee.kotlin.backend.domain.entity

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
    val id: UUID,
    @Column(unique = true)
    val name: String,
    val password: String,
    val contents: String,
    val createdAt: Instant
)