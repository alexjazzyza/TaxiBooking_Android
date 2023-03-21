package fr.jazzyza.taxibooking.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpFormData(@SerializedName("phoneNumber") val phoneNumber: String = "",
                          @SerializedName("email") val email: String = "",
                          @SerializedName("password") val password: String = "",
                          @SerializedName("firstName") val firstName: String ="",
                          @SerializedName("lastName") val lastName: String ="") {


}
