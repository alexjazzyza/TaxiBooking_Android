package fr.jazzyza.taxibooking.security

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sessionManager: SessionManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        val url = originalRequest.url.toString()
        if (!url.endsWith("sign-in/") && !url.endsWith("sign-up/")) {
            sessionManager.readAccessToken()?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}

