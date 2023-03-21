package fr.jazzyza.taxibooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.jazzyza.taxibooking.screens.MainScreen
import fr.jazzyza.taxibooking.screens.TaxiBookingSplashScreen
import fr.jazzyza.taxibooking.screens.TaxiBookingSplashScreenLogin
import fr.jazzyza.taxibooking.screens.auth.signIn.SignIn
import fr.jazzyza.taxibooking.screens.signIn.SignUpPhoneNumber
import fr.jazzyza.taxibooking.screens.auth.SignUpDone
import fr.jazzyza.taxibooking.screens.auth.SignUpEmail
import fr.jazzyza.taxibooking.screens.auth.SignUpName
import fr.jazzyza.taxibooking.screens.auth.SignUpPassword

@Composable
fun TaxiBookingNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ETaxiBookingScreens.SPLASH_SCREEN.name) {

        composable(ETaxiBookingScreens.SPLASH_SCREEN.name) {
            TaxiBookingSplashScreen(navController = navController)
        }

        composable(ETaxiBookingScreens.SPLASH_SCREEN_LOGIN.name) {
            TaxiBookingSplashScreenLogin(navController = navController)
        }

        composable(ETaxiBookingScreens.SIGNUP_PHONE_SCREEN.name) {
            SignUpPhoneNumber(navController = navController)
        }

        composable(ETaxiBookingScreens.SIGNUP_PASSWORD_SCREEN.name + "/{phoneNumber}") {
            val phoneNumber = it.arguments?.getString("phoneNumber") ?: ""
            SignUpPassword(navController = navController, phoneNumber = phoneNumber)
        }

        composable(ETaxiBookingScreens.SIGNUP_EMAIL_SCREEN.name + "/{phoneNumber}/{password}") {
            val phoneNumber = it.arguments?.getString("phoneNumber") ?: ""
            val password = it.arguments?.getString("password") ?: ""
            SignUpEmail(navController = navController, phoneNumber = phoneNumber, password = password)
        }

        composable(ETaxiBookingScreens.SIGNUP_NAME_SCREEN.name + "/{phoneNumber}/{password}/{email}") {
            val phoneNumber = it.arguments?.getString("phoneNumber") ?: ""
            val password = it.arguments?.getString("password") ?: ""
            val email = it.arguments?.getString("email") ?: ""
            SignUpName(navController = navController, phoneNumber = phoneNumber, password = password, email = email)
        }

        composable(ETaxiBookingScreens.SIGNUP_DONE_SCREEN.name + "/{phoneNumber}/{password}/{email}/{firstName}/{lastName}") {
            val phoneNumber = it.arguments?.getString("phoneNumber") ?: ""
            val password = it.arguments?.getString("password") ?: ""
            val email = it.arguments?.getString("email") ?: ""
            val firstName = it.arguments?.getString("firstName") ?: ""
            val lastName = it.arguments?.getString("lastName") ?: ""
            SignUpDone(navController = navController, phoneNumber = phoneNumber, password = password, email = email, firstName = firstName, lastName = lastName)
        }

        composable(ETaxiBookingScreens.SIGNIN_SCREEN.name) {
            SignIn(navController = navController)
        }

        composable(ETaxiBookingScreens.MAIN_SCREEN.name) {
            MainScreen(navController = navController)
        }
    }

}