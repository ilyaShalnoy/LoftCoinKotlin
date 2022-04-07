package com.example.notes.loftcoinkotlin.data.wallets

import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.WalletsRepository
import com.example.notes.loftcoinkotlin.data.Transaction
import com.example.notes.loftcoinkotlin.data.currency.Currency
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletsRepositoryImpl @Inject constructor(private val coinsRepository: CoinsRepository) : WalletsRepository {

    private val fireStore = FirebaseFirestore.getInstance()

    override fun wallet(currency: Currency): Observable<List<Wallet>> {
        return Observable.create<QuerySnapshot> { emitter ->
            val registration = fireStore
                .collection("wallets")
                .addSnapshotListener { value, error ->
                    if (emitter.isDisposed) return@addSnapshotListener
                    if (value != null) {
                        emitter.onNext(value)
                    } else if (error != null) {
                        emitter.tryOnError(error)
                    }
                }
            emitter.setCancellable(registration::remove)
        }.map(QuerySnapshot::getDocuments)
            .switchMapSingle { documents ->
                Observable
                    .fromIterable(documents)
                    .flatMapSingle { document ->
                        coinsRepository.coin(requireNotNull(document.getLong("coinId")), currency)
                            .map { coin ->
                                Wallet.create(
                                    document.id,
                                    coin,
                                    document.getDouble("balance")!!
                                )
                            }
                    }.toList()
            }
    }

    override fun transaction(wallet: Wallet): Observable<List<Transaction>> {
        return Observable.create<QuerySnapshot> { emitter ->
            val registration = fireStore
                .collection("wallets")
                .document(wallet.getUid())
                .collection("transactions")
                .addSnapshotListener { value, error ->
                    if (emitter.isDisposed) return@addSnapshotListener
                    if (value != null) {
                        emitter.onNext(value)
                    } else if (error != null) {
                        emitter.tryOnError(error)
                    }
                }
            emitter.setCancellable(registration::remove)
        }.map(QuerySnapshot::getDocuments)
            .switchMapSingle { documents ->
                Observable
                    .fromIterable(documents)
                    .map { document ->
                        Transaction.create(
                            document.id,
                            wallet.getCoin(),
                            document.getDouble("amount")!!,
                            document.getDate("at")!!
                        )
                    }.toList()
            }
    }

    override fun addWallet(currency: Currency, takenIds: List<Int>) {
        return
    }
}