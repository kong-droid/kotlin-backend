package coffee.kotlin.backend.domain.entity
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.Instant
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "memo")
data class MemoEntity (
    @Id
    @Column(updatable = false)
    @GenericGenerator(name = "system", strategy = "uuid2")
    @GeneratedValue(generator = "system")
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val name: String,
    val password: String,
    val contents: String,
    @Column(updatable = false)
    @CreationTimestamp
    val createdAt: Instant = Instant.now()
)