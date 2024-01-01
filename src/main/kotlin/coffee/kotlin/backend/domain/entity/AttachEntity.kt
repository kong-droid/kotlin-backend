package coffee.kotlin.backend.domain.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "attach")
data class AttachEntity(
    @Id
    @Column(updatable = false)
    val id: UUID = UUID.randomUUID(),
    val groupId: UUID?,
    val originName: String,
    val targetName: String,
    val path: String,
    val size: Int,
    @Column(updatable = false)
    @CreationTimestamp
    val createdAt: Instant = Instant.now()
)