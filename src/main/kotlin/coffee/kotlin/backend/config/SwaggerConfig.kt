package coffee.kotlin.backend.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.beans.factory.annotation.Configurable

@OpenAPIDefinition(
    info = Info(
        title = "Kotlin Memo API",
        description = "kotlin study",
        version = "v1.0.0"
    )
)
@Configurable
class SwaggerConfig {
    // add config.. but not setting security.
}