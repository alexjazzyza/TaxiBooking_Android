package fr.jazzyza.taxibooking.validation

class PasswordConfirmState: TextFieldState (
    validator = { true },
    validator2 = { passwordConfirmation, password -> isPasswordConfirmationValid(passwordConfirmation, password) },
    errorMessage = { "Passwords don't match" }
) {

}

private fun isPasswordConfirmationValid(passwordConfirmation: String, password: String): Boolean {
    return password == passwordConfirmation
}
