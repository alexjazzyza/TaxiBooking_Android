package fr.jazzyza.taxibooking

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import fr.jazzyza.taxibooking.navigation.TaxiBookingNavigation
import fr.jazzyza.taxibooking.ui.theme.TaxiBookingTheme
import fr.jazzyza.taxibooking.data.viewModel.CustomerViewModel
import fr.jazzyza.taxibooking.utils.AppColors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private val applicationViewModel: ApplicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val location by applicationViewModel.getLocationLiveData().observeAsState()
            TaxiBookingApp()
        }
//        prepLocationUpdates()
    }
}

@Composable
fun TaxiBookingApp() {
    TaxiBookingTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColors.mDarkBackground
        ) {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
//                GPS(location)
                TaxiBookingNavigation()
            }
        }
    }
}

@Composable
fun Test (customerViewModel: CustomerViewModel = hiltViewModel()) {

    val customer = customerViewModel.data.value.data

//    customerViewModel.getCustomerById(1)

    if (customerViewModel.data.value.loading == true) {
        Log.d("Loading", "Customer loading")
    } else {
        Log.d("Loading", "Customer loaded")
        Log.d("Result", "Customer's first name: ${customer?.firstName}")
        Log.d("Result", "Customer's last name: ${customer?.lastName}")
    }
}

//private fun prepLocationUpdates() {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
//        requestLocationUpdates()
//    } else {
//        requestSinglePermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//    }
//}
//
//private fun requestLocationUpdates() {
//    applicationViewModel
//}
//
//@Composable
//fun GPS(location: LocationDetails?) {
//    location?.let {
//        Text(text = location.latitude,
//            color = MaterialTheme.colors.surface,
//            style = MaterialTheme.typography.h5)
//        Text(text = location.longitude,
//            color = MaterialTheme.colors.surface,
//            style = MaterialTheme.typography.h5)
//    }
//}

