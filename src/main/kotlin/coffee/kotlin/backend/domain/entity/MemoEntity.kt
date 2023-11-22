package coffee.kotlin.backend.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "memo")
data class MemoEntity (
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID =UUID.randomUUID(),
    @Column(unique = true)
    val name: String,
    val password: String,
    val contents: String,
    @CreationTimestamp
    val createdAt: Instant
)