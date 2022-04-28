package com.example.notes.loftcoinkotlin.data.net

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY_HEADER = "X-CMC_PRO_API_KEY"

interface CoinsApi {

    @GET("cryptocurrency/listings/latest")
    fun fetchListings(@Query("convert") convert: String): Observable<Listings>

}