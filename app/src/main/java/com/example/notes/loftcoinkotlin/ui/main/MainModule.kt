package com.example.notes.loftcoinkotlin.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.notes.loftcoinkotlin.core.LoftFragmentFactory
import com.example.notes.loftcoinkotlin.ui.converter.ConverterFragment
import com.example.notes.loftcoinkotlin.ui.currency.CurrencyDialogFragment
import com.example.notes.loftcoinkotlin.ui.rates.RatesFragment
import com.example.notes.loftcoinkotlin.ui.wallets.WalletsFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    abstract fun fragmentFactory(fragmentFactory: LoftFragmentFactory): FragmentFactory

    @Binds
    @[IntoMap ClassKey(RatesFragment::class)]
    abstract fun ratesFragment(impl: RatesFragment): Fragment

    @Binds
    @[IntoMap ClassKey(WalletsFragment::class)]
    abstract fun walletFragment(impl: WalletsFragment): Fragment

    @Binds
    @[IntoMap ClassKey(ConverterFragment::class)]
    abstract fun converterFragment(impl: ConverterFragment): Fragment

    @Binds
    @[IntoMap ClassKey(CurrencyDialogFragment::class)]
    abstract fun currencyDialogFragment(impl: CurrencyDialogFragment): Fragment


}