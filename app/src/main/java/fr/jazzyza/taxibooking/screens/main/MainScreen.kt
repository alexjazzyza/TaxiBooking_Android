package fr.jazzyza.taxibooking.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import fr.jazzyza.taxibooking.data.viewModel.CustomerViewModel
import fr.jazzyza.taxibooking.navigation.ETaxiBookingScreens
import fr.jazzyza.taxibooking.screens.auth.AuthViewModel
import fr.jazzyza.taxibooking.utils.AppColors

@Composable
fun MainScreen(navController: NavController, customerViewModel: CustomerViewModel = hiltViewModel(), authViewModel: AuthViewModel = hiltViewModel()) {

    LaunchedEffect(customerViewModel.data.value.loading) {
        if (customerViewModel.data.value.loading == false) {
            Log.d("Loading", "Customer loaded")
            Log.d("Result", "Customer's first name: ${customerViewModel.data.value.data?.firstName}")
            Log.d("Result", "Customer's last name: ${customerViewModel.data.value.data?.lastName}")
        } else if (customerViewModel.data.value.e?.localizedMessage != ""){
            Log.d("Loading", "Error")
        }
    }

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            FloatingActionButton(onClick = { customerViewModel.getCustomerById(2) }) {
                Text(text = "Press")
            }
            FloatingActionButton(onClick = {
                authViewModel.logOut()
                navController.navigate(ETaxiBookingScreens.SPLASH_SCREEN_LOGIN.name)}) {
                Text(text = "Log Out")
            }
        }
        MapDisplay()
        AddressPrompt()
    }
}

@Composable
fun MapDisplay() {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 15f)
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}

@Composable
fun AddressPrompt () {
    Surface(modifier = Modifier
        .fillMaxSize(),
        elevation = 8.dp,
        color = MaterialTheme.colors.background) {
        Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(40.dp, 20.dp, 40.dp, 20.dp)) {
            Text(text = "HI USER !",
                color = AppColors.mPurple,
                style = MaterialTheme.typography.h5)
            Text(text = "WHERE ARE YOU GOING ?",
                color = Color.White,
                style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(value = "Enter a destination", onValueChange = {})
        }
    }
}
