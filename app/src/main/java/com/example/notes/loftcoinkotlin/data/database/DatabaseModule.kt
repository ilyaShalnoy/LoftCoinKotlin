package com.example.notes.loftcoinkotlin.data.database

import android.content.Context
import androidx.room.Room
import com.example.notes.loftcoinkotlin.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun loftDatabase(context: Context): LoftDatabase =
        if (BuildConfig.DEBUG) {
            Room.inMemoryDatabaseBuilder(context, LoftDatabase::class.java).build()
        } else {
            Room.databaseBuilder(context, LoftDatabase::class.java, "loft.db").build()
        }
}
