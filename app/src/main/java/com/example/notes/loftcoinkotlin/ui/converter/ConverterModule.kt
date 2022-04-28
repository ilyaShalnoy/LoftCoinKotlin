package com.example.notes.loftcoinkotlin.ui.converter

import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.util.ViewModelKey
import com.example.notes.loftcoinkotlin.ui.currency.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ConverterModule {

    @Binds
    @[IntoMap ViewModelKey(ConverterViewModel::class)]
    abstract fun converterViewModel(impl: ConverterViewModel): ViewModel

}