package fr.jazzyza.taxibooking.validation

import java.util.regex.Pattern

class EmailState: TextFieldState(
    validator = ::isEmailValid,
    errorMessage = ::emailErrorMessage
) {}

//private const val EMAIL_VALIDATION_REGEX = "/^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$/"
private const val EMAIL_VALIDATION_REGEX = "[a-zA-Z0-9+._%\\-]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
//private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

private fun emailErrorMessage(email: String) = "Email $email is invalid."