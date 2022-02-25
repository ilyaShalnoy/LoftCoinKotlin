package com.example.notes.loftcoinkotlin.ui.main

import com.example.notes.loftcoinkotlin.core.BaseComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class],
dependencies = [
    BaseComponent::class
])
abstract class MainComponent {

    abstract fun inject(mainActivity: MainActivity)

}