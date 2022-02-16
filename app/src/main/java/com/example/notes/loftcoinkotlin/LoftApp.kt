package com.example.notes.loftcoinkotlin

import android.app.Application
import android.os.StrictMode
import com.example.notes.loftcoinkotlin.core.util.DebugTree
import com.example.notes.loftcoinkotlin.data.net.API_KEY_HEADER
import com.example.notes.loftcoinkotlin.data.net.CloudCoinsRepository
import com.example.notes.loftcoinkotlin.data.net.CoinsApi
import com.example.notes.loftcoinkotlin.ui.rates.RatesViewModelFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class LoftApp : Application() {

    private lateinit var coinsRepository: CloudCoinsRepository

    val ratesViewModelFactory by lazy {
        RatesViewModelFactory(coinsRepository)
    }

    override fun onCreate() {
        super.onCreate()

        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
            Timber.plant(DebugTree());
            with(loggingInterceptor) {
                level = HttpLoggingInterceptor.Level.HEADERS
                redactHeader(API_KEY_HEADER)
            }
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(API_KEY_HEADER, BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            })
            .addInterceptor(loggingInterceptor)
            .build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val coinsApi = retrofit.create(CoinsApi::class.java)

        coinsRepository = CloudCoinsRepository(coinsApi)
    }
}