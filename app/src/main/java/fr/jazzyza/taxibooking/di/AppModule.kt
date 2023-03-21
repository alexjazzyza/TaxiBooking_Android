package fr.jazzyza.taxibooking.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.jazzyza.taxibooking.network.TaxiBookingApi
import fr.jazzyza.taxibooking.repository.CustomerRepository
import fr.jazzyza.taxibooking.data.viewModel.ApplicationViewModel
import fr.jazzyza.taxibooking.security.AuthAuthenticator
import fr.jazzyza.taxibooking.security.AuthInterceptor
import fr.jazzyza.taxibooking.security.SessionManager
import fr.jazzyza.taxibooking.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationViewModel(application: Application): ApplicationViewModel = ApplicationViewModel(application)

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor, authAuthenticator: AuthAuthenticator): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideTaxiBookingApi(okHttpClient: OkHttpClient): TaxiBookingApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TaxiBookingApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthAuthenticator(sessionManager: SessionManager): AuthAuthenticator =
        AuthAuthenticator(sessionManager)

    @Singleton
    @Provides
    fun provideEncryptionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

    @Singleton
    @Provides
    fun provideCustomerRepository(api: TaxiBookingApi) = CustomerRepository(api)
}
