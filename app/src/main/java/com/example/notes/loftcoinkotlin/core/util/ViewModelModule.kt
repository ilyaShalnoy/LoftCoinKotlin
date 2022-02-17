package com.example.notes.loftcoinkotlin.core.util

import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun viewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

}