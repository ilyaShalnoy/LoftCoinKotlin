package com.example.notes.loftcoinkotlin.ui.wallets

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.data.wallets.Wallet
import com.example.notes.loftcoinkotlin.databinding.FragmentWalletsBinding
import com.example.notes.loftcoinkotlin.ui.BaseFragment
import com.example.notes.loftcoinkotlin.ui.widget.RxRecyclerViews
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.pow

class WalletsFragment @Inject constructor(baseComponent: BaseComponent) : BaseFragment<FragmentWalletsBinding>() {

    private lateinit var walletsAdapter: WalletsAdapter
    private lateinit var transactionAdapter: TransactionAdapter

    private lateinit var viewModel: WalletsViewModel

    private val component = DaggerWalletsComponent.builder()
        .baseComponent(baseComponent)
        .build()

    private val disposable = CompositeDisposable()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWalletsBinding {
        return FragmentWalletsBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        walletsAdapter = component.walletsAdapter()
        transactionAdapter = component.transactionAdapter()

        viewModel = ViewModelProvider(this, component.viewModelFactory())[WalletsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val walletsSnapHelper = PagerSnapHelper()
        walletsSnapHelper.attachToRecyclerView(binding.recyclerForCards)

        calculationPadding(view)

        with(binding.recyclerForCards) {
            adapter = walletsAdapter
            layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            addOnScrollListener(CarouselScroller)
        }

        disposable.add(viewModel.getWallets().subscribe(walletsAdapter::submitList))
        disposable.add(viewModel.getWallets().map(List<Wallet>::isEmpty).subscribe { isEmpty ->
            binding.addCardWallets.visibility = if (isEmpty) View.VISIBLE else View.GONE
            binding.recyclerForCards.visibility = if (isEmpty) View.GONE else View.VISIBLE
        })

        with(binding.recyclerCardTransactions) {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
        disposable.add(viewModel.getTransactions().subscribe(transactionAdapter::submitList))

        disposable.add(RxRecyclerViews
            .onSnap(binding.recyclerForCards, walletsSnapHelper)
            .subscribe { position -> viewModel.changeWallet(position) })
    }

    override fun onDestroyView() {
        binding.recyclerForCards.adapter = null
        binding.recyclerCardTransactions.adapter = null
        super.onDestroyView()
    }

    private fun calculationPadding(view: View) {
        val typedValue = TypedValue()
        view.context.theme.resolveAttribute(R.attr.walletCardWidth, typedValue, true)
        val displayMetrics = view.context.resources.displayMetrics
        val padding: Int = ((displayMetrics.widthPixels - typedValue.getDimension(displayMetrics)) / 2).toInt()
        binding.recyclerForCards.setPadding(padding, 0, padding, 0)
        binding.recyclerForCards.clipToPadding = false
    }

    private object CarouselScroller : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val centerX = (recyclerView.left + recyclerView.right) / 2
            for (index in 0 until recyclerView.childCount) {
                val child = recyclerView.getChildAt(index)
                val childCenterX = (child.left + child.right) / 2
                val childOffSet = abs(centerX - childCenterX) / centerX
                val factor = 0.8.pow(childOffSet)
                child.scaleX = factor.toFloat()
                child.scaleY = factor.toFloat()
            }
        }
    }


}