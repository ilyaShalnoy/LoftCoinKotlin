package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.BaseComponent
import com.example.notes.loftcoinkotlin.core.util.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RatesModule::class, ViewModelModule::class],
    dependencies = [BaseComponent::class]
)
abstract class RatesComponent {

    abstract fun viewModelFactory(): ViewModelProvider.Factory
}