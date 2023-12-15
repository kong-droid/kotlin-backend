package coffee.kotlin.backend.exception.handler

import coffee.kotlin.backend.constants.messages.ErrorMessage
import coffee.kotlin.backend.domain.response.common.ApiErrorResponse
import coffee.kotlin.backend.exception.custom.AuthException
import coffee.kotlin.backend.exception.custom.DuplicatedException
import coffee.kotlin.backend.exception.custom.InvalidStateException
import coffee.kotlin.backend.exception.custom.NotFoundException
import org.springframework.context.MessageSource
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@RestControllerAdvice(annotations = [RestController::class])
class CustomExceptionHandler(messageSource: MessageSource): BaseExceptionHandler(messageSource) {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ApiErrorResponse? {
        return toResponse(ex)
    }

    @ExceptionHandler(DuplicatedException::class)
    fun handleDuplicated(ex: DuplicatedException): ApiErrorResponse? {
        return toResponse(ex)
    }

    @ExceptionHandler(InvalidStateException::class)
    fun handleInvalidState(ex: InvalidStateException): ApiErrorResponse? {
        return toResponse(ex)
    }

    @ExceptionHandler(AuthException::class)
    fun handleAuth(ex: AuthException?): Any? {
        return ex?.let { toResponse(it) }
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun validationViolation(ex: ConstraintViolationException): Any? {
        println("파라미터 오류 확인 : \t ${ex.constraintViolations.size}")
        val violation = ex.constraintViolations.toTypedArray()[0] as ConstraintViolation<*>
        val message = "[" + violation.propertyPath + "] " + violation.message
        return toResponse(ErrorMessage.REQUEST_QUERY_PARAMETER, arrayOf<String>(message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationViolation(ex: MethodArgumentNotValidException): Any? {
        val arg = ex.bindingResult.errorCount.toString()
        return toResponse(ErrorMessage.REQUEST_BODY_FIELD, arrayOf<String>(arg))
    }
}