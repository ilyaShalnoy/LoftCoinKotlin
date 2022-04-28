package com.example.notes.loftcoinkotlin.ui.wallets

import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.core.data.WalletsRepository
import com.example.notes.loftcoinkotlin.core.util.RxSchedulers
import com.example.notes.loftcoinkotlin.data.Transaction
import com.example.notes.loftcoinkotlin.data.wallets.Wallet
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import timber.log.Timber
import javax.inject.Inject

class WalletsViewModel @Inject constructor(
    private val walletsRepo: WalletsRepository,
    private val currencyRepo: CurrencyRepository,
    private val rxSchedulers: RxSchedulers
) : ViewModel() {

    private val walletPosition: Subject<Int> = BehaviorSubject.createDefault(0)

    private val wallets = currencyRepo.currency()
        .switchMap(walletsRepo::wallet)
        .replay(1)
        .autoConnect()


    private val transaction: Observable<List<Transaction>> =
        wallets.switchMap { walletsList ->
            walletPosition.map(walletsList::get)
        }.switchMap(walletsRepo::transaction)
            .replay(1)
            .autoConnect()

    fun getWallets(): Observable<List<Wallet>> = wallets.observeOn(rxSchedulers.main())

    fun getTransactions(): Observable<List<Transaction>> = transaction.observeOn(rxSchedulers.main())

    fun changeWallet(position: Int) {
        walletPosition.onNext(position)
    }

}

