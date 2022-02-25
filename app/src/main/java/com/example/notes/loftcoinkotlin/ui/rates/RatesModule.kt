package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class RatesModule {

    @Binds
    @[IntoMap ViewModelKey(RatesViewModel::class)]
    abstract fun ratesViewModel(impl: RatesViewModel): ViewModel

}