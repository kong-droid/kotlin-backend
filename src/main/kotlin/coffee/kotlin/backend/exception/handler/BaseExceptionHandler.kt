package coffee.kotlin.backend.exception.handler

import coffee.kotlin.backend.constants.messages.ErrorMessage
import coffee.kotlin.backend.domain.response.common.ApiErrorResponse
import coffee.kotlin.backend.exception.custom.BaseException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.lang.Nullable
import java.util.*

open class BaseExceptionHandler(private val messageSource: MessageSource) {
    fun getMessage(target: String, @Nullable args: Array<String?>?): String {
        return getMessage(target, args, LocaleContextHolder.getLocale())
    }

    fun getMessage(target: String, @Nullable args: Array<String?>?, locale: Locale): String {
        return messageSource.getMessage(target, args, locale)
    }

    fun toResponse(exception: BaseException): ApiErrorResponse? {
        val error: ErrorMessage? = exception.error;
        return error?.let { toResponse(it, null) }
    }

    fun toResponse(error: ErrorMessage, args: Array<String?>?): ApiErrorResponse? {
        val message = getMessage(error.resName(), args)
        return ApiErrorResponse(error.getCode(), message)
    }
}