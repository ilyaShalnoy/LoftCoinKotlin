package com.example.notes.loftcoinkotlin.ui.converter

import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.ui.ViewModelModule
import com.example.notes.loftcoinkotlin.ui.currency.CurrencyModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ConverterModule::class, ViewModelModule::class],
    dependencies = [BaseComponent::class]
)
abstract class ConverterComponent {

    abstract fun viewModelFactory(): ViewModelProvider.Factory

    abstract fun coinsSheetAdapter(): CoinsSheetAdapter
}