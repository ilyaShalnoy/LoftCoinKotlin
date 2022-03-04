package com.example.notes.loftcoinkotlin.ui.currency

import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CurrencyModule {

    @Binds
    @[IntoMap ViewModelKey(CurrencyViewModel::class)]
    abstract fun currencyViewModel(impl: CurrencyViewModel): ViewModel
}