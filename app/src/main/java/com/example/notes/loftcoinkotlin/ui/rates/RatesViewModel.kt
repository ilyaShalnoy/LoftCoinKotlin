package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.data.CoinsRepository
import com.example.notes.loftcoinkotlin.data.net.Coin
import java.lang.Exception
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject

class RatesViewModel @Inject constructor(private val repository: CoinsRepository) : ViewModel() {

    private val _coinsLiveData = MutableLiveData<List<Coin>>()
    val coinsLiveData get() = _coinsLiveData

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing get() = _isRefreshing

    private lateinit var future: Future<*>

    private val executor = Executors.newSingleThreadExecutor()

    fun refresh() {
        _isRefreshing.postValue(true)
        future = executor.submit {
            try {
                _coinsLiveData.postValue(repository.fetchListings("USD"))
                isRefreshing.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        future.cancel(true)
    }


}