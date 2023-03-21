package fr.jazzyza.taxibooking.validation

import java.util.regex.Pattern

class PhoneNumberState: TextFieldState(
    validator = ::isPhoneNumberValid,
    errorMessage = ::phoneNumberErrorMessage
) {}

private const val PHONE_NUMBER_REGEX = "^[0-9]{10}\$"

fun isPhoneNumberValid(phoneNumber: String): Boolean {
    return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber)
}

fun phoneNumberErrorMessage(phoneNumber: String): String = "Phone number $phoneNumber is not valid"