package coffee.kotlin.backend.exception.custom

import coffee.kotlin.backend.constants.messages.ErrorMessage
import org.springframework.core.NestedRuntimeException
import lombok.experimental.Accessors

open class BaseException(
    message: String?,
    cause: Throwable?,
    @Accessors(fluent = true) private val error: ErrorMessage?
) : NestedRuntimeException(message, cause)