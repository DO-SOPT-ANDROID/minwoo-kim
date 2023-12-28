package org.sopt.dosopttemplate.data.model.remote.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig.AUTH_BASE_URL
import org.sopt.dosopttemplate.BuildConfig.FOLLOWER_BASE_URL
import org.sopt.dosopttemplate.data.service.AuthService
import org.sopt.dosopttemplate.data.service.HomeService
import retrofit2.Retrofit

object ApiFactory {
    private fun getLogOkHttpClient(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit2", "CONNECTION INFO -> $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    fun getRetrofit(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    inline fun <reified T, B> create(url: B): T =
        getRetrofit(url.toString()).create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService, String>(AUTH_BASE_URL)
    val followerService = ApiFactory.create<HomeService, String>(FOLLOWER_BASE_URL)
}