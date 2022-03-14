package com.example.notes.loftcoinkotlin.core.util

import dagger.Binds
import dagger.Module

@Module
abstract class UtilModule {

    @Binds
    abstract fun imageLoader(impl: PicassoImageLoader): ImageLoader

    @Binds
    abstract fun schedulers(impl: RxSchedulersImpl): RxSchedulers
}