package coffee.kotlin.backend.domain.entity.common

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue

open class UuidEntity (
    @Column(updatable = false, columnDefinition = "uuid")
    @GenericGenerator(name = "system", strategy = "uuid2")
    @GeneratedValue(generator = "system")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    open val id: UUID
);