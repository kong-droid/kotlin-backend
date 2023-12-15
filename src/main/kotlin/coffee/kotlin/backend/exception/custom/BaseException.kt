package coffee.kotlin.backend.exception.custom

import coffee.kotlin.backend.constants.messages.ErrorMessage
import org.springframework.core.NestedRuntimeException

open class BaseException(
    message: String?,
    cause: Throwable?,
    val error: ErrorMessage
) : NestedRuntimeException(message, cause)