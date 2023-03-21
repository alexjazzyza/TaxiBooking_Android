package fr.jazzyza.taxibooking.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.jazzyza.taxibooking.data.LocationLiveData
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val locationLiveData = LocationLiveData(application)
    fun getLocationLiveData() = locationLiveData
    fun startLocationUpdates() {
        locationLiveData.startLocationUpdates()
    }
}