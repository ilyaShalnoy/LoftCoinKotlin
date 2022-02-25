package com.example.notes.loftcoinkotlin.data.net

import com.example.notes.loftcoinkotlin.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

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

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun coinsApi(retrofit: Retrofit): CoinsApi =
        retrofit.create(CoinsApi::class.java)

}
