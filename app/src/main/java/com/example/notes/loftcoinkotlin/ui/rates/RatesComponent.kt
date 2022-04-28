package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.ui.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RatesModule::class, ViewModelModule::class],
    dependencies = [BaseComponent::class]
)
abstract class RatesComponent {

    abstract fun viewModelFactory(): ViewModelProvider.Factory

    abstract fun ratesAdapter(): RatesAdapter
}