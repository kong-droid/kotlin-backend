package coffee.kotlin.backend.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class LocalDateToInstantConverter {
    fun convert(date: LocalDate?, hour: Int, minute: Int, second: Int): Instant? {
        return date?.atTime(hour, minute, second)?.toInstant(ZoneOffset.of("+09:00"))
    }
}