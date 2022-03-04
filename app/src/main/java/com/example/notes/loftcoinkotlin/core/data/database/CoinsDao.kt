package com.example.notes.loftcoinkotlin.core.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.loftcoinkotlin.data.database.CacheCoin

@Dao
interface CoinsDao {

    @Query("SELECT * FROM CacheCoin")
    fun fetchAll(): LiveData<List<CacheCoin>>

    @Query("SELECT * FROM CacheCoin ORDER BY rank ASC")
    fun fetchAllSortByRank(): LiveData<List<CacheCoin>>

    @Query("SELECT * FROM CacheCoin ORDER BY price DESC")
    fun fetchAllSortByPrice(): LiveData<List<CacheCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins: List<CacheCoin>)

    @Query("SELECT COUNT(id) FROM CacheCoin")
    fun coinsCount(): Int
}