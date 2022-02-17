package com.example.notes.loftcoinkotlin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.ui.rates.RatesViewModel
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory @Inject constructor(private val providers: Map<Class<*>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = providers[modelClass]
        return if (provider != null) {
            provider.get() as T
        } else super.create(modelClass)
    }
}