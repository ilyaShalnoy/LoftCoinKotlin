package com.example.notes.loftcoinkotlin.ui.wallets

import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class WalletsModule {

    @Binds
    @[IntoMap ViewModelKey(WalletsViewModel::class)]
    abstract fun walletsViewModel(impl: WalletsViewModel): ViewModel

}