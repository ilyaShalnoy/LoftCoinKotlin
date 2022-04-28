package com.example.notes.loftcoinkotlin.ui.currency

import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.ui.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CurrencyModule::class, ViewModelModule::class],
dependencies = [BaseComponent::class])
abstract class CurrencyComponent {

    abstract fun viewModelFactory(): ViewModelProvider.Factory

}