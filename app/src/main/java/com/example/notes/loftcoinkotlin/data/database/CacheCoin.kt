package com.example.notes.loftcoinkotlin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.loftcoinkotlin.core.Mapper
import com.example.notes.loftcoinkotlin.data.CoinsDataModel

@Entity
data class CacheCoin(
    @PrimaryKey val id: Int,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val change24: Double,
    val currencyCode: String
): Mapper<CoinsDataModel> {
    override fun to(): CoinsDataModel {
        return CoinsDataModel(id, name, symbol, rank, price, change24, currencyCode)
    }
}


