package com.example.notes.loftcoinkotlin.core.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.loftcoinkotlin.data.database.CacheCoin
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface CoinsDao {

    @Query("SELECT * FROM CacheCoin")
    fun fetchAll(): Observable<List<CacheCoin>>

    @Query("SELECT * FROM CacheCoin WHERE id =:id")
    fun fetchOne(id: Long): Single<CacheCoin>

    @Query("SELECT * FROM CacheCoin ORDER BY rank ASC")
    fun fetchAllSortByRank(): Observable<List<CacheCoin>>

    @Query("SELECT * FROM CacheCoin ORDER BY price DESC")
    fun fetchAllSortByPrice(): Observable<List<CacheCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins: List<CacheCoin>)

    @Query("SELECT COUNT(id) FROM CacheCoin")
    fun coinsCount(): Int

    @Query("SELECT * FROM CacheCoin ORDER BY rank ASC LIMIT :limit")
    fun fetchTop(limit: Int): Observable<List<CacheCoin>>

}