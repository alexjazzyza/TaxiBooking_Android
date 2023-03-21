package fr.jazzyza.taxibooking.screens.auth

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.jazzyza.taxibooking.data.dto.SignUpFormData
import fr.jazzyza.taxibooking.components.BottomButton
import fr.jazzyza.taxibooking.components.HeaderText
import fr.jazzyza.taxibooking.components.BackButton
import fr.jazzyza.taxibooking.data.dto.SignInFormData
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.utils.AppColors
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun SignUpDone(navController: NavController, phoneNumber: String, password: String, email: String, firstName: String, lastName: String, authViewModel: AuthViewModel = hiltViewModel()) {

    val phoneNumberState = remember { mutableStateOf(phoneNumber) }
    val passwordState = remember { mutableStateOf(password) }
    val emailState = remember { mutableStateOf(email) }
    val firstNameState = remember { mutableStateOf(firstName) }
    val lastNameState = remember { mutableStateOf(lastName) }

    LaunchedEffect(authViewModel.signUpResponseData.value.data?.success) {
        if (authViewModel.signUpResponseData.value.data != null && authViewModel.signUpResponseData.value.data!!.success) {
            val signInFormData = SignInFormData(
                containsPassword = true,
                usernameOrEmail = phoneNumberState.value,
                password = passwordState.value,
                refreshToken = ""
            )
            authViewModel.signIn(signInFormData)
        }
    }

    LaunchedEffect(authViewModel.isLoggedIn.value) {
        if (authViewModel.isLoggedIn.value) {
            navController.navigate(ETaxiBookingScreens.MAIN_SCREEN.name)
        }
    }


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
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                HeaderText(text = "ALMOST DONE !", color = Color.White, fontSize = 32)
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)) {
                    withStyle(style = SpanStyle(color = AppColors.mLightGrey)) {
                        append(text = "By clicking on the bottom button,\nyou accept ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Taxi Booking's terms and conditions")
                    }
                }
            })
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 60.dp)) {
            BottomButton(color = AppColors.mPurple,
                text = "I agree",
                isEnabled = true) {
                val formData = SignUpFormData(
                    phoneNumber = phoneNumberState.value,
                    email = emailState.value,
                    password = passwordState.value,
                    firstName = firstNameState.value,
                    lastName = lastNameState.value)
                authViewModel.signUp(formData)
            }
        }
    }
}
