package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.data.CoinsRepository
import com.example.notes.loftcoinkotlin.data.net.Coin
import java.lang.Exception
import java.util.concurrent.Executors
import java.util.concurrent.Future

class RatesViewModel(private val repository: CoinsRepository) : ViewModel() {

    private val _coinsLiveData = MutableLiveData<List<Coin>>()
    val coinsLiveData get() = _coinsLiveData

    private lateinit var future: Future<*>

    private val executor = Executors.newSingleThreadExecutor()

    fun refresh() {
        future = executor.submit {
            try {
                _coinsLiveData.postValue(repository.fetchListings("USD"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        future.cancel(true)
    }


}