package fr.jazzyza.taxibooking.validation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState (
    private val validator: (String) -> Boolean= { true },
    private val validator2: (String, String) -> Boolean = { _: String, _: String -> true },
    private val errorMessage: (String) -> String) {

    var text by mutableStateOf("")
    var error by mutableStateOf<String?>(null)

    fun validate() {
        error = if (!validator(text)) errorMessage(text) else null
    }

    fun validate(password: String) {
        error = if (!validator2(text, password)) errorMessage(text) else null
    }

}