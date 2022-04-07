package com.example.notes.loftcoinkotlin.data.fcm

import com.example.notes.loftcoinkotlin.core.BaseComponent
import dagger.Component

@Component(
    modules = [FcmModule::class],
    dependencies = [BaseComponent::class]
)
abstract class FcmComponent {

    abstract fun inject(fcmService: FcmService)

}