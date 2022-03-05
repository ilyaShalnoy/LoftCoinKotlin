package com.example.notes.loftcoinkotlin.core

import android.app.Application
import android.content.Context
import com.example.notes.loftcoinkotlin.BuildConfig
import com.example.notes.loftcoinkotlin.data.net.API_KEY_HEADER
import com.squareup.picasso.Downloader
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun context(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun ioExecutor(): ExecutorService {
        val poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1
        return Executors.newFixedThreadPool(poolSize)
    }

    @Singleton
    @Provides
    fun provideHttpClient(executor: ExecutorService): OkHttpClient {
        val buildHttpClient = OkHttpClient.Builder()
            .dispatcher(Dispatcher(executor))
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

    @Singleton
    @Provides
    fun providePicasso(context: Context, httpClient: OkHttpClient, executor: ExecutorService): Picasso {
        return Picasso.Builder(context)
            .downloader(OkHttp3Downloader(httpClient))
            .executor(executor)
            .build()
    }
}