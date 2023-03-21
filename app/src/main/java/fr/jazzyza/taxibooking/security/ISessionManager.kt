package fr.jazzyza.taxibooking.security

interface ISessionManager {

    fun saveOrRemoveTokens(accessToken: String?, refreshToken:String?)
    fun readAccessToken(): String?
    fun readRefreshToken(): String?
}