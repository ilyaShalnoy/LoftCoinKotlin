package com.example.notes.loftcoinkotlin.ui.wallets

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.databinding.FragmentWalletsBinding
import com.example.notes.loftcoinkotlin.ui.BaseFragment
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.pow


class WalletsFragment @Inject constructor(): BaseFragment<FragmentWalletsBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWalletsBinding {
        return FragmentWalletsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val walletsSnapHelper = PagerSnapHelper()
        walletsSnapHelper.attachToRecyclerView(binding.recyclerForCards)

        calculationPadding(view)

        with(binding.recyclerForCards) {
            adapter = WalletsAdapter()
            layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            addOnScrollListener(CarouselScroller)
        }

        binding.recyclerForCards.visibility = View.VISIBLE
        binding.addCardWallets.visibility = View.GONE

    }

    override fun onDestroyView() {
        binding.recyclerForCards.adapter = null
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