package coffee.kotlin.backend.exception.custom

import coffee.kotlin.backend.constants.messages.ErrorMessage

class AuthException(error: ErrorMessage?): BaseException(null, null, error)