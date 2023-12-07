package coffee.kotlin.backend.controller.base

import coffee.kotlin.backend.constants.messages.SuccessMessage
import coffee.kotlin.backend.domain.response.common.ApiSuccessResponse
import coffee.kotlin.backend.domain.response.common.PageResponse
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.data.domain.Page
import java.util.Optional


class BaseController(private val messageSource: MessageSource) {
    fun <T> wrap(data: T): ApiSuccessResponse<T> {
        var success: SuccessMessage = SuccessMessage.EMPTY;

        if(data is PageResponse<*> && data.totalCount > 0) {
            success = SuccessMessage.RESULT;
        } else if (data is List<*> && data.size > 0) {
            success = SuccessMessage.RESULT;
        } else if (data != null) {
            success = SuccessMessage.RESULT;
        }

        val message:String = messageSource.getMessage(success.resName(), null, LocaleContextHolder.getLocale());
        return ApiSuccessResponse(success.getCode(), message, data);
    }

    fun <T> page(data: Page<T>): ApiSuccessResponse<PageResponse<T>> {
        return wrap(PageResponse(data));
    }

    fun <T> optional(data: Optional<T>): ApiSuccessResponse<T?> {
        if(data.isPresent) return wrap(data.get());
        else return wrap(null);
    }

}