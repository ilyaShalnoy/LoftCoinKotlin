package com.example.notes.loftcoinkotlin.ui.wallets

import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.ui.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [WalletsModule::class, ViewModelModule::class],
    dependencies = [BaseComponent::class]
)
abstract class WalletsComponent {

    abstract fun viewModelFactory(): ViewModelProvider.Factory

    abstract fun walletsAdapter(): WalletsAdapter

    abstract fun transactionAdapter(): TransactionAdapter

}