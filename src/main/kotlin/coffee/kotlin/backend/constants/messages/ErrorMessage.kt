package coffee.kotlin.backend.constants.messages


enum class ErrorMessage(val code: String) {

    REQUEST_QUERY_PARAMETER("100"),
    REQUEST_BODY_FIELD("101"),
    IO_CHECK_FILE_NAME("200"),
    IO_CANNOT_CREATE_FOLDER("201"),
    COMMON_NOT_FOUND("404"),
    FILE_NOT_FOUND("500"),
    MEMO_NOT_FOUND("1000"),
    MEMO_PASSWORD_MISMATCH("1100");

    fun getCode(): Int {
        return Integer.parseInt(code);
    }

    fun resName(): String {
        return "response.error." + name.lowercase().replace(Regex("[_]"), ".") + ".message";
    }

    open fun from(code: String): ErrorMessage {
        for (value in ErrorMessage.entries) {
            if(value.name == code) return value;
        }
        throw IllegalArgumentException(code);
    }

}