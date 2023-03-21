package fr.jazzyza.taxibooking.data.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.jazzyza.taxibooking.data.payloads.ApiResponse
import fr.jazzyza.taxibooking.models.Customer
import fr.jazzyza.taxibooking.repository.CustomerRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: CustomerRepository) : ViewModel() {

    val data: MutableState<ApiResponse<Customer, Boolean, Exception>> = mutableStateOf(ApiResponse(null, true, Exception("")))

//    init {
//        getCustomerById(1)
//    }

    fun getCustomerById(id: Long) {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getCustomerById(id)
            if (data.value.data != null && data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
                Log.d("ViewModel", "loaded")
            }
        }
    }
}