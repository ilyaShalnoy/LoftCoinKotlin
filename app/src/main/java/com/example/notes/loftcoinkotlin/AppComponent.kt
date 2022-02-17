package com.example.notes.loftcoinkotlin

import android.app.Application
import android.content.Context
import com.example.notes.loftcoinkotlin.data.CoinsRepository
import com.example.notes.loftcoinkotlin.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.data.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
abstract class AppComponent : BaseComponent {

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun application(app: Application): Builder
        abstract fun build(): AppComponent
    }


}

