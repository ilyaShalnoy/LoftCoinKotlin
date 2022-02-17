package com.example.notes.loftcoinkotlin.data

import com.example.notes.loftcoinkotlin.BuildConfig
import com.example.notes.loftcoinkotlin.data.net.API_KEY_HEADER
import com.example.notes.loftcoinkotlin.data.net.CloudCoinsRepository
import com.example.notes.loftcoinkotlin.data.net.CoinsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient {
            val buildHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .header(API_KEY_HEADER, BuildConfig.API_KEY)
                        .build()
                    chain.proceed(request)
                })

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                with(loggingInterceptor) {
                    level = HttpLoggingInterceptor.Level.HEADERS
                    redactHeader(API_KEY_HEADER)
                }
                buildHttpClient.addInterceptor(loggingInterceptor)
            }
            return buildHttpClient.build()
        }

        @JvmStatic
        @Provides
        fun provideMoshi(): Moshi =
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        @JvmStatic
        @Provides
        fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @JvmStatic
        @Provides
        fun coinsApi(retrofit: Retrofit): CoinsApi =
            retrofit.create(CoinsApi::class.java)
    }

    @Binds
    abstract fun coinsRepo(cloudCoinsRepo: CloudCoinsRepository): CoinsRepository
    @Binds
    abstract fun currencyRepo(currencyRepo: CurrencyRepositoryImpl): CurrencyRepository

}