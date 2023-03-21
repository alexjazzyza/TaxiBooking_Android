package fr.jazzyza.taxibooking.data.payloads

data class SignInResponseData(val accessToken: String,
                              val refreshToken: String,
                              val tokenType: String)