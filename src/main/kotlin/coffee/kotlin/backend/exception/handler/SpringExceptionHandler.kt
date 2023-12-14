package coffee.kotlin.backend.exception.handler

import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException
import java.util.*
import javax.servlet.ServletException

@RestControllerAdvice(annotations = [RestController::class])
class SpringExceptionHandler(messageSource: MessageSource) : BaseExceptionHandler(messageSource) {
    @ExceptionHandler(ServletException::class)
    fun handleException(t: Throwable): Any? {
        val message: String? = t.message
        println("$message: \t ${t.localizedMessage}")
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(message)
    }

    @ExceptionHandler(SQLException::class)
    fun handleSqlError(ex: SQLException): Any? {
        var message = ex.message
        println("${ex.errorCode}: $message")
        try {
            val startIdx = message!!.indexOf("ERROR:") + 6
            val endIdx = message.indexOf("Detail:")
            if (startIdx >= 0 && startIdx < endIdx) message = message.substring(startIdx, endIdx)
            println("SQL Exception : \t $message")
        } catch (e: Exception) {
            println("SQL Message Error!!! : \t $message")
        }
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(message)
    }
}