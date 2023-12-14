package coffee.kotlin.backend.exception.handler

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.lang.Nullable
import java.util.Locale

class SpringExceptionHandler(private val messageSource: MessageSource) {

    fun getMessage(target: String, @Nullable args: Array<String>): String {
        return getMessage(target, args, LocaleContextHolder.getLocale())
    }

    fun getMessage(target: String, @Nullable args: Array<String>, locale: Locale): String {
        return messageSource.getMessage(target, args, locale)
    }


}