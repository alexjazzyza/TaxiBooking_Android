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
import fr.jazzyza.taxibooking.components.BottomButton
import fr.jazzyza.taxibooking.components.HeaderText
import fr.jazzyza.taxibooking.components.TextInput
import fr.jazzyza.taxibooking.components.BackButton
import fr.jazzyza.taxibooking.utils.AppColors

@Composable
fun SignUpName(navController: NavController, phoneNumber: String, password: String, email: String) {

    val phoneNumberState = remember { mutableStateOf(phoneNumber) }
    val passwordState = remember { mutableStateOf(password) }
    val emailState = remember { mutableStateOf(email)}
    val firstNameState = remember { mutableStateOf("") }
    val lastNameState = remember { mutableStateOf("") }

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
                horizontalArrangement = Arrangement.Start
            ) {
                HeaderText(text = "NICE TO MEET YOU !", color = Color.White)
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            TextInput(text = firstNameState.value,
                textLabel = "First name",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                maxChar = 24) {
                firstNameState.value = it
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            TextInput(text = lastNameState.value,
                textLabel = "Last name",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done),
                maxChar = 24) {
                lastNameState.value = it
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 60.dp)) {

            val enableButton = (firstNameState.value != "" && lastNameState.value != "")

            BottomButton(color = AppColors.mPurple,
                text = "Confirm",
                isEnabled = enableButton) {
                val formData = SignUpFormData(phoneNumber = phoneNumberState.value,
                    email = emailState.value,
                    password = passwordState.value,
                    firstName = firstNameState.value,
                    lastName = lastNameState.value)
                navController.navigate(ETaxiBookingScreens.SIGNUP_DONE_SCREEN.name + "/${formData.phoneNumber}/${formData.password}/${formData.email}/${formData.firstName}/${formData.lastName}")
            }
        }
    }
}