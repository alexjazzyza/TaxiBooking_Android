package fr.jazzyza.taxibooking.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.jazzyza.taxibooking.components.BottomButton
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.utils.AppColors

@Composable
fun TaxiBookingSplashScreenLogin(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.mDarkBackground) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 60.dp)) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "TAXI BOOKING",
                    color = AppColors.mPurple,
                    style = MaterialTheme.typography.h3
                )
            }
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Book a cab for a fair price and enjoy your ride !",
                    color = AppColors.mLightGrey,
                    style = MaterialTheme.typography.body1
                )
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(30.dp, 60.dp)) {
            Row() {
                BottomButton(color = AppColors.mPurple,
                    text = "Sign In",
                    isEnabled = true) {
                    navController.navigate(ETaxiBookingScreens.SIGNIN_SCREEN.name)
                }
            }
            Spacer(modifier = Modifier.padding(0.dp, 10.dp))
            Row() {
                BottomButton(color = AppColors.mPurple,
                    text = "Sign Up",
                    isEnabled = true) {
                    navController.navigate(ETaxiBookingScreens.SIGNUP_PHONE_SCREEN.name)
                }
            }
        }
    }
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