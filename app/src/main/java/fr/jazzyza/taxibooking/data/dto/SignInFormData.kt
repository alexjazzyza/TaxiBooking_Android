package fr.jazzyza.taxibooking.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable

@Serializable
data class SignInFormData(@SerializedName("containsPassword") @EncodeDefault val containsPassword: Boolean = true,
                          @SerializedName("usernameOrEmail") @EncodeDefault val usernameOrEmail: String = "",
                          @SerializedName("password") @EncodeDefault val password: String = "",
                          @SerializedName("refreshToken") @EncodeDefault val refreshToken: String = "")