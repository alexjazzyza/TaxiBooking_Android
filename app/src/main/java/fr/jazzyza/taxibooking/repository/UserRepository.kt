package fr.jazzyza.taxibooking.repository

import android.util.Log
import fr.jazzyza.taxibooking.data.payloads.ApiResponse
import fr.jazzyza.taxibooking.data.payloads.SignInResponseData
import fr.jazzyza.taxibooking.data.payloads.BasicResponseData
import fr.jazzyza.taxibooking.models.User
import fr.jazzyza.taxibooking.network.TaxiBookingApi
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: TaxiBookingApi) {

    private val user = ApiResponse<User, Boolean, Exception>()
    private val signUpResponse = ApiResponse<BasicResponseData, Boolean, Exception>()
    private val signInResponse = ApiResponse<SignInResponseData, Boolean, Exception>()
    private val logOutResponse = ApiResponse<BasicResponseData, Boolean, Exception>()

    suspend fun signUp(formData: String?): ApiResponse<BasicResponseData, Boolean, Exception> {
        try {
            signUpResponse.loading = true
            signUpResponse.data = api.signUp(formData)
            if (signUpResponse.data != null) {
                signUpResponse.loading = false
                Log.d("Repo", "loaded")
                Log.d("Response Success", signUpResponse.data!!.success.toString())
                Log.d("Response Message", signUpResponse.data!!.message)
            }
        } catch (exception: Exception) {
            signUpResponse.e = exception
            Log.d("API RESPONSE", "createCustomer: ${signUpResponse.e!!.localizedMessage}")
        }
        return signUpResponse
    }

    suspend fun signIn(formData: String?): ApiResponse<SignInResponseData, Boolean, Exception> {
        try {
            signInResponse.loading = true
            signInResponse.data = api.signIn(formData)
            if (signInResponse.data != null) {
                signInResponse.loading = false
                Log.d("Repo", "loaded")
                Log.d("Response Success", signInResponse.data!!.accessToken)
                Log.d("Response Message", signInResponse.data!!.refreshToken)
                Log.d("Response Message", signInResponse.data!!.tokenType)
            }
        } catch (exception: Exception) {
            signInResponse.e = exception
            Log.d("API RESPONSE", "Sign in user: ${signInResponse.e!!.localizedMessage}")
        }
        return signInResponse
    }

    suspend fun logOut(): ApiResponse<BasicResponseData, Boolean, Exception> {
        try {
            logOutResponse.loading = true
            logOutResponse.data = api.logOut()
            if (logOutResponse.data != null) {
                logOutResponse.loading = false
            }
        } catch (exception: Exception) {
            logOutResponse.e = exception
            Log.d("API RESPONSE", "logout: ${logOutResponse.e!!.localizedMessage}")
        }
        return logOutResponse
    }
}