package com.example.notes.loftcoinkotlin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.loftcoinkotlin.data.net.Quote
import com.squareup.moshi.Json

@Entity
data class CacheCoin(
    @PrimaryKey val id: Int,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val change24: Double
)
