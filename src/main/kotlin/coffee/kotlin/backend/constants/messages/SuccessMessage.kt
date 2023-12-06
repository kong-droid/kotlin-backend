package coffee.kotlin.backend.constants.messages

import lombok.experimental.Accessors

enum class SuccessMessage(@Accessors(fluent = true) val code: String) {

    RESULT("0"), EMPTY("1");

    fun getCode(): Int {
        return Integer.parseInt(code);
    }

    fun resName(): String {
        return "response.success." + name.lowercase().replace(Regex("[_]"), ".") + ".message";
    }

    fun from(code: String): SuccessMessage {
        for (value in SuccessMessage.entries) {
            if(value.name == code) return value;
        }
        throw IllegalArgumentException(code);
    }
}