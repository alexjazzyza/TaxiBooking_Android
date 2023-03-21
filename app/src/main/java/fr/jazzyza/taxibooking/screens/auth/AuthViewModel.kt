package fr.jazzyza.taxibooking.screens.auth

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.jazzyza.taxibooking.data.dto.SignInFormData
import fr.jazzyza.taxibooking.data.dto.SignUpFormData
import fr.jazzyza.taxibooking.data.payloads.ApiResponse
import fr.jazzyza.taxibooking.data.payloads.SignInResponseData
import fr.jazzyza.taxibooking.data.payloads.BasicResponseData
import fr.jazzyza.taxibooking.repository.UserRepository
import fr.jazzyza.taxibooking.security.SessionManager
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository, private val sessionManager: SessionManager) : ViewModel() {

    val signUpResponseData: MutableState<ApiResponse<BasicResponseData, Boolean, Exception>> = mutableStateOf(ApiResponse(null, true, Exception("")))
    val signInResponseData: MutableState<ApiResponse<SignInResponseData, Boolean, Exception>> = mutableStateOf(ApiResponse(null, true, Exception("")))
    val logOutResponseData: MutableState<ApiResponse<BasicResponseData, Boolean, Exception>> = mutableStateOf(ApiResponse(null, true, Exception("")))


    private var accessToken: String? = null
    private var refreshToken: String? = null

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    init {
        _isLoggedIn.value = refreshToken != null
    }

    fun signUp(formData: SignUpFormData) {
        val jsonString = Json.encodeToString(formData)
        viewModelScope.launch {
            signUpResponseData.value.loading = true
            signUpResponseData.value = userRepository.signUp(jsonString)
            if (signUpResponseData.value.data != null) {
                signUpResponseData.value.loading = false
            }
        }
    }

    fun signIn(formData: SignInFormData) {
        val jsonString = Json.encodeToString(formData)
        viewModelScope.launch {
            signInResponseData.value.loading = true
            signInResponseData.value = userRepository.signIn(jsonString)
            if (signInResponseData.value.data != null) {
                signInResponseData.value.loading = false
                accessToken = signInResponseData.value.data!!.accessToken
                refreshToken = signInResponseData.value.data!!.refreshToken
                sessionManager.saveOrRemoveTokens(accessToken, refreshToken)
                _isLoggedIn.value = (accessToken != null && refreshToken != null)
            } else {
                _error.value = signInResponseData.value.e?.localizedMessage.toString()
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logOutResponseData.value.loading = true
            logOutResponseData.value = userRepository.logOut()
            if (logOutResponseData.value.data != null) {
                logOutResponseData.value.loading = false
                sessionManager.saveOrRemoveTokens(null, null)
                _isLoggedIn.value = false
            }
        }
    }
}