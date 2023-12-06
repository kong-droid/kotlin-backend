package coffee.kotlin.backend.constants.messages

import lombok.experimental.Accessors

enum class ErrorMessage(@Accessors(fluent = true) val code: String) {

    REQUEST_QUERY_PARAMETER("100"),
    REQUEST_BODY_FIELD("101"),
    IO_CHECK_FILE_NAME("200"),
    IO_CANNOT_CREATE_FOLDER("201"),
    COMMON_NOT_FOUND("404");

    fun getCode(): Int {
        return Integer.parseInt(code);
    }

    fun resName(): String {
        return "response.error." + name.lowercase().replace(Regex("[_]"), ".") + ".message";
    }

    fun from(code: String): SuccessMessage {
        for (value in SuccessMessage.entries) {
            if(value.name == code) return value;
        }
        throw IllegalArgumentException(code);
    }

}