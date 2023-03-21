package fr.jazzyza.taxibooking.network

import fr.jazzyza.taxibooking.models.Customer
import fr.jazzyza.taxibooking.data.payloads.SignInResponseData
import fr.jazzyza.taxibooking.data.payloads.BasicResponseData
import fr.jazzyza.taxibooking.utils.Constants
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface TaxiBookingApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET(Constants.CUSTOMER_END_POINT + "{id}/")
    suspend fun getCustomerById(@Path("id") id : Long): Customer?

//    @GET(Constants.USER_END_POINT + "{id}/")
//    suspend fun getUSerById(@Path("id") id : Long): User?

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(Constants.AUTH_END_POINT + "sign-up/")
    suspend fun signUp(@Body formData: String?): BasicResponseData

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(Constants.AUTH_END_POINT + "sign-in/")
    suspend fun signIn(@Body formData: String?): SignInResponseData?

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(Constants.AUTH_END_POINT + "sign-in/")
    suspend fun refreshToken(@Body formData: String?): Response<SignInResponseData?>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET(Constants.AUTH_END_POINT + "logout/")
    suspend fun logOut(): BasicResponseData
}