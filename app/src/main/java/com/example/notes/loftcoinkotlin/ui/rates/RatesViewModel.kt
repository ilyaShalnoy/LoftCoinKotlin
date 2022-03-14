package com.example.notes.loftcoinkotlin.ui.rates

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.core.data.SortBy
import com.example.notes.loftcoinkotlin.core.util.RxSchedulers
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class RatesViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository,
    private val currencyRepository: CurrencyRepository,
    private val schedulers: RxSchedulers
) : ViewModel() {

    private val _isRefreshing: Subject<Boolean> = BehaviorSubject.create()

    val isRefreshing: Observable<Boolean>
        get() = _isRefreshing.observeOn(schedulers.main())

    private val pullToRefresh = BehaviorSubject.createDefault(Void.TYPE)

    private val sortBy = BehaviorSubject.createDefault(SortBy.RANK)

    private val forceUpdate = AtomicBoolean()

    private var sortingIndex = 1

    // qb = QueryBuilder
    fun coins(): Observable<List<CoinsDataModel>> = pullToRefresh
        .observeOn(schedulers.main())
        .map {
            Query.Builder
        }.switchMap { qb ->
            currencyRepository.currency().map { c ->
                qb.currency(c.getCode())
            }
        }.doOnNext {
            forceUpdate.set(true)
        }.doOnNext {
            _isRefreshing.onNext(true)
        }.switchMap { qb ->
            sortBy.map { s ->
                qb.sortBy(s)
            }
        }.map { qb ->
            qb.forceUpdate(forceUpdate.getAndSet(false))
        }.map {
            Query.build()
        }.switchMap { query ->
            coinsRepository.fetchListingsDatabase(query)
        }.doOnEach {
            _isRefreshing.onNext(false)
        }


    fun refresh() {
        pullToRefresh.onNext(Void.TYPE)
    }

    fun switchSortingOrder() {
        val sort = SortBy.values()[sortingIndex++ % SortBy.values().size]
        sortBy.onNext(sort)
    }


}