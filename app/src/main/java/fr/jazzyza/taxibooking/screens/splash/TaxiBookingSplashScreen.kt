package fr.jazzyza.taxibooking.screens


import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.jazzyza.taxibooking.data.dto.SignInFormData
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.screens.auth.AuthViewModel
import fr.jazzyza.taxibooking.security.SessionManager
import fr.jazzyza.taxibooking.utils.AppColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun TaxiBookingSplashScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val opacity = remember {
        Animatable(0f)
    }

    val sessionManager = SessionManager(LocalContext.current)

    LaunchedEffect(true) {
        opacity.animateTo(1f, tween(2000, 0) { OvershootInterpolator(1f).getInterpolation(it) })
        delay(2000L)

        if (sessionManager.readRefreshToken() != null) {
            val formData = SignInFormData(
                containsPassword = false,
                refreshToken = sessionManager.readRefreshToken().toString()
            )
            authViewModel.signIn(formData)
        } else {
            navController.navigate(ETaxiBookingScreens.SPLASH_SCREEN_LOGIN.name)
        }
    }

    LaunchedEffect(authViewModel.signInResponseData.value) {
        if (authViewModel.signInResponseData.value.loading == false) {
            if (authViewModel.isLoggedIn.value) {
                navController.navigate(ETaxiBookingScreens.MAIN_SCREEN.name)
            }
        }
        if (authViewModel.error.value != "") {
            navController.navigate(ETaxiBookingScreens.SPLASH_SCREEN_LOGIN.name)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.mDarkBackground) {
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "TAXI BOOKING",
                    Modifier.alpha(opacity.value),
                    color = AppColors.mPurple,
                    style = MaterialTheme.typography.h3
                )
            }
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Book a cab for a fair price and enjoy your ride !",
                    Modifier.alpha(opacity.value),
                    color = AppColors.mLightGrey,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

suspend fun checkSignIn(authViewModel: AuthViewModel, formData: SignInFormData): Boolean {
    authViewModel.signIn(formData)
    if (authViewModel.signInResponseData.value.loading == false) {
        return authViewModel.isLoggedIn.value
    }
    return false
}

//
//@Composable
//fun PrintLocationDetails(applicationViewModel: ApplicationViewModel) {
//    applicationViewModel.startLocationUpdates()
//    Text(text = applicationViewModel.getLocationLiveData().value?.longitude.toString(),
//        color = MaterialTheme.colors.surface,
//        style = MaterialTheme.typography.h5)
//    Text(text = applicationViewModel.getLocationLiveData().value?.latitude.toString(),
//        color = MaterialTheme.colors.surface,
//        style = MaterialTheme.typography.h5)
//}