package fr.jazzyza.taxibooking.screens.signIn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import fr.jazzyza.taxibooking.validation.PhoneNumberState

@Composable
fun SignUpPhoneNumber(navController: NavController) {

    val phoneNumberState = remember { PhoneNumberState() }

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = AppColors.mDarkBackground) {
        BackButton(modifier = Modifier.size(35.dp)) {
            navController.popBackStack()
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 60.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.Start) {
                HeaderText("HELLO !", AppColors.mPurple)
            }
            Row(horizontalArrangement = Arrangement.Start) {
                HeaderText("WHAT IS YOUR PHONE NUMBER ?", Color.White)
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            TextInput(text =  phoneNumberState.text ,
                error = phoneNumberState.error,
                textLabel = "Phone Number",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                maxChar = 10) {
                phoneNumberState.text = it
                phoneNumberState.validate()
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 60.dp)) {

            var enableButton = (phoneNumberState.error == null && phoneNumberState.text != "")

            BottomButton(color = AppColors.mPurple,
                text = "Confirm",
                isEnabled = enableButton) {
                val formData = SignUpFormData(phoneNumber = phoneNumberState.text,
                                            email = "",
                                            password = "",
                                            firstName = "",
                                            lastName = "")
                navController.navigate(ETaxiBookingScreens.SIGNUP_PASSWORD_SCREEN.name + "/${formData.phoneNumber}")
            }
        }
    }
}



