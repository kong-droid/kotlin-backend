package coffee.kotlin.backend.domain.entity.common

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.domain.Persistable
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
data class UuidEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Getter
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: UUID? = UUID.randomUUID()
): Persistable<UUID> {
    override fun getId(): UUID? {
        return id;
    }

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val createdAt: Instant = Instant.now()
    @JsonIgnore
    override fun isNew(): Boolean {
       return id == null;
    }

}
