package fr.jazzyza.taxibooking.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.jazzyza.taxibooking.data.dto.SignUpFormData
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.components.*
import fr.jazzyza.taxibooking.utils.AppColors
import fr.jazzyza.taxibooking.validation.EmailState


@Composable
fun SignUpEmail(navController: NavController, phoneNumber: String, password: String) {

    val phoneNumberState = remember { mutableStateOf(phoneNumber) }
    val passwordState = remember { mutableStateOf(password) }
    val emailState = remember { EmailState() }

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = AppColors.mDarkBackground) {
        BackButton(modifier = Modifier.size(35.dp)) {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                HeaderText(text = "AND YOUR EMAIL", color = Color.White)
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            TextInput(text = emailState.text,
                error = emailState.error ,
                textLabel = "Email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done),
                maxChar = 50) {
                emailState.text = it
                emailState.validate()
            }
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))
            SubText(text = "You will get your reservations' invoices at this address", color = AppColors.mDarkGrey)
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 60.dp)) {

            var enableButton = (emailState.error == null && emailState.text != "")

            BottomButton(color = AppColors.mPurple,
                text = "Confirm",
                isEnabled = enableButton) {
                val formData = SignUpFormData(phoneNumber = phoneNumberState.value,
                    email = emailState.text,
                    password = passwordState.value,
                    firstName = "",
                    lastName = "")
                navController.navigate(ETaxiBookingScreens.SIGNUP_NAME_SCREEN.name + "/${formData.phoneNumber}/${formData.password}/${formData.email}")
            }
        }
    }
}