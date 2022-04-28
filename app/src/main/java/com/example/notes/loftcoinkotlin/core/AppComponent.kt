package com.example.notes.loftcoinkotlin.core

import android.app.Application
import com.example.notes.loftcoinkotlin.core.util.UtilModule
import com.example.notes.loftcoinkotlin.data.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        UtilModule::class
    ]
)
interface AppComponent : BaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }


}

