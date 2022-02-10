package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.data.CoinsRepository
import java.lang.IllegalArgumentException

class RatesViewModelFactory(private val cloudCoinsRepository: CoinsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RatesViewModel::class.java))
            RatesViewModel(cloudCoinsRepository) as T
        else
            throw IllegalArgumentException("ViewModel not found")
    }

}