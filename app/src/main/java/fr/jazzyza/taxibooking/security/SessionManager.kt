package fr.jazzyza.taxibooking.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import fr.jazzyza.taxibooking.utils.Constants.ACCESS_TOKEN_KEY
import fr.jazzyza.taxibooking.utils.Constants.REFRESH_TOKEN_KEY
import javax.inject.Inject

class SessionManager @Inject constructor(context: Context): ISessionManager {

    private val sharedPreferences: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

    override fun saveOrRemoveTokens(accessToken: String?, refreshToken: String?) {
        if (refreshToken == null) {
            removeTokens()
        } else {
            saveTokens(accessToken, refreshToken)
        }
    }

    private fun saveTokens(accessToken: String?, refreshToken: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN_KEY, accessToken)
        editor.putString(REFRESH_TOKEN_KEY, refreshToken)
        editor.apply()
    }

    private fun removeTokens() {
        val editor = sharedPreferences.edit()
        editor.remove(ACCESS_TOKEN_KEY)
        editor.remove(REFRESH_TOKEN_KEY)
        editor.apply()
    }

    override fun readAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    override fun readRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
    }
}