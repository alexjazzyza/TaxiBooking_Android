package fr.jazzyza.taxibooking.repository

import android.util.Log
import fr.jazzyza.taxibooking.data.payloads.ApiResponse
import fr.jazzyza.taxibooking.models.Customer
import fr.jazzyza.taxibooking.network.TaxiBookingApi
import javax.inject.Inject
import kotlin.Exception


class CustomerRepository @Inject constructor(private val api: TaxiBookingApi) {

    private val customer = ApiResponse<Customer, Boolean, Exception>()

    suspend fun getCustomerById(id: Long): ApiResponse<Customer, Boolean, Exception> {
        try {
            customer.loading = true
            customer.data = api.getCustomerById(id)
            if (customer.data != null && customer.data.toString().isNotEmpty()) {
                customer.loading = false
            }
        } catch (exception: Exception) {
            customer.e = exception
            Log.d("API RESPONSE", "getCustomerById: ${customer.e!!.localizedMessage}")
        }
        return customer
    }
}