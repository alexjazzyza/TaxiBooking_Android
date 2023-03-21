package fr.jazzyza.taxibooking.screens.auth.signIn

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.jazzyza.taxibooking.components.*
import fr.jazzyza.taxibooking.data.dto.SignInFormData
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.screens.auth.AuthViewModel
import fr.jazzyza.taxibooking.utils.AppColors
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun SignIn(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val phoneNumberOrEmailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(authViewModel.signInResponseData.value) {
        Log.d("TEST", "Sign In")
        if (authViewModel.signInResponseData.value.loading == false) {
            if (authViewModel.isLoggedIn.value) {
                navController.navigate(ETaxiBookingScreens.MAIN_SCREEN.name)
            }
        }
        if (authViewModel.error.value != "") {
            Toast.makeText(context, "Bad credentials", Toast.LENGTH_SHORT).show()
        }
    }


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
                HeaderText(text = "ENTER YOUR LOGIN CREDENTIALS", color = Color.White)
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            TextInput(text = phoneNumberOrEmailState.value,
                textLabel = "Phone number/email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                maxChar = 24) {
                phoneNumberOrEmailState.value = it
            }
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            PasswordTextInput(text = passwordState.value,
                textLabel = "Password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done),
                maxChar = 24) {
                passwordState.value = it
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 30.dp)) {

            var enableButton = (phoneNumberOrEmailState.value.isNotEmpty() && passwordState.value.isNotEmpty())

            BottomButton(color = AppColors.mPurple,
                text = "Confirm",
                isEnabled = enableButton) {
                val formData = SignInFormData(
                    containsPassword = true,
                    usernameOrEmail = phoneNumberOrEmailState.value,
                    password = passwordState.value,
                    refreshToken = "")
                authViewModel.signIn(formData)
            }
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            TextButton(textColor = AppColors.mPurple,
                backgroundColor = AppColors.mDarkBackground,
                text = "I forgot my password") {
//                navController.navigate(ETaxiBookingScreens.MAIN_SCREEN.name)
            }
        }
    }
}