package com.example.notes.loftcoinkotlin.ui.widget

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import io.reactivex.rxjava3.android.MainThreadDisposable
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

class RxRecyclerViews {

    companion object {

        @JvmStatic
        fun onSnap(rv: RecyclerView, helper: SnapHelper): Observable<Int> {
            return Observable.create { emitter ->
                MainThreadDisposable.verifyMainThread()
                val listener = object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                            helper.findSnapView(rv.layoutManager)?.let { snapView ->
                                rv.findContainingViewHolder(snapView)?.let { holder ->
                                    Timber.d("onSnap")
                                    emitter.onNext(holder.adapterPosition)
                                }
                            }
                        }
                    }
                }
                emitter.setCancellable {
                    rv.removeOnScrollListener(listener)
                }
            }
        }
    }
}