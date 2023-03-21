package fr.jazzyza.taxibooking.validation

import java.util.regex.Pattern

class PasswordState: TextFieldState (
    validator = ::isPasswordValid,
    errorMessage = { "" }
        ) {
}

private const val PASSWORD_VALIDATION_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"

private fun isPasswordValid(password: String): Boolean {
    return Pattern.matches(PASSWORD_VALIDATION_REGEX, password)
}
