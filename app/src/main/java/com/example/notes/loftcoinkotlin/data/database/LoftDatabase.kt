package com.example.notes.loftcoinkotlin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.loftcoinkotlin.core.data.database.CoinsDao

@Database(entities = [CacheCoin::class], version = 1)
abstract class LoftDatabase : RoomDatabase() {

    abstract fun getDao(): CoinsDao
}