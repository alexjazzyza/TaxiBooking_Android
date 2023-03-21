package fr.jazzyza.taxibooking.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.jazzyza.taxibooking.data.dto.SignUpFormData
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.components.*
import fr.jazzyza.taxibooking.utils.AppColors
import fr.jazzyza.taxibooking.validation.PasswordConfirmState
import fr.jazzyza.taxibooking.validation.PasswordState

@Composable
fun SignUpPassword(navController: NavController, phoneNumber: String) {

    val phoneNumberState = remember { mutableStateOf(phoneNumber) }
    val passwordState = remember { PasswordState() }
    val passwordConfirmState = remember { PasswordConfirmState() }

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = AppColors.mDarkBackground) {
        BackButton(modifier = Modifier.size(35.dp)) {
            navController.popBackStack()
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {

            val focusManager = LocalFocusManager.current

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                HeaderText(text = "TYPE YOUR PASSWORD", color = Color.White)
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            PasswordTextInput(text = passwordState.text,
                error = passwordState.error,
                textLabel = "Password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                maxChar = 24) {
                passwordState.text = it
                passwordState.validate()
            }
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))
            SubText(text = "8 characters minimum", color = AppColors.mDarkGrey)
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))
            SubText(text = "1 lower case letter minimum", color = AppColors.mDarkGrey)
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))
            SubText(text = "1 upper case letter minimum", color = AppColors.mDarkGrey)
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))
            SubText(text = "1 number minimum", color = AppColors.mDarkGrey)
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))
            SubText(text = "1 special character minimum", color = AppColors.mDarkGrey)
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            PasswordTextInput(text = passwordConfirmState.text,
                error = passwordConfirmState.error,
                textLabel = "Confirm password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done),
                maxChar = 24) {
                passwordConfirmState.text = it
                passwordConfirmState.validate(passwordState.text)
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 60.dp)) {

            var enableButton = (passwordState.error == null && passwordConfirmState.error == null && passwordState.text != "" && passwordConfirmState.text != "")

            BottomButton(color = AppColors.mPurple,
                text = "Confirm",
                isEnabled = enableButton) {
                val formData = SignUpFormData(phoneNumber = phoneNumberState.value,
                    email = "",
                    password = passwordState.text,
                    firstName = "",
                    lastName = "")
                navController.navigate(ETaxiBookingScreens.SIGNUP_EMAIL_SCREEN.name + "/${formData.phoneNumber}/${formData.password}")
            }
        }
    }
}