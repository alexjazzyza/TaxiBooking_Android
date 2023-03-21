package fr.jazzyza.taxibooking.security

import android.util.Log
import fr.jazzyza.taxibooking.data.dto.SignInFormData
import fr.jazzyza.taxibooking.data.payloads.SignInResponseData
import fr.jazzyza.taxibooking.network.TaxiBookingApi
import fr.jazzyza.taxibooking.utils.Constants.AUTH_END_POINT
import fr.jazzyza.taxibooking.utils.Constants.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(private val sessionManager: SessionManager): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            sessionManager.readRefreshToken()
        }

        if (refreshToken != null && refreshToken != "") {
            val signInResponse = runBlocking {
                getNewToken(refreshToken)
            }
            if (!signInResponse.isSuccessful || signInResponse.body() == null) { //Couldn't refresh the token, so restart the login process
                sessionManager.saveOrRemoveTokens(null, null)
                return null
            } else {
                sessionManager.saveOrRemoveTokens(signInResponse.body()?.accessToken, signInResponse.body()?.refreshToken)
            }
            return response.request.newBuilder()
                .header("Authorization", "Bearer ${signInResponse.body()?.accessToken}")
                .build()
        } else return null
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<SignInResponseData?> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(TaxiBookingApi::class.java)
        val formData = SignInFormData(
            containsPassword = false,
            refreshToken = refreshToken!!
        )
        val jsonString = Json.encodeToString(formData)
        return service.refreshToken(jsonString)
    }
}