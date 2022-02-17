package com.example.notes.loftcoinkotlin.ui.rates

import android.graphics.Color
import android.graphics.Outline
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.BuildConfig
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.util.Formatter
import com.example.notes.loftcoinkotlin.core.util.OutlineCircle
import com.example.notes.loftcoinkotlin.data.net.Coin
import com.example.notes.loftcoinkotlin.databinding.LiRateBinding
import com.example.notes.loftcoinkotlin.ui.rates.RatesAdapter.RatesViewHolder
import com.squareup.picasso.Picasso
import kotlin.math.min

class RatesAdapter(
    private val priceFormatter: Formatter<Double>,
    private val changeFormatter: Formatter<Double>
) : ListAdapter<Coin, RatesViewHolder>(RatesDiffCallback()) {

    private lateinit var inflater: LayoutInflater
    private var colorNegative = Color.RED
    private var colorPositive = Color.GREEN

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(LiRateBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val context = recyclerView.context

        inflater = LayoutInflater.from(context)

        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.percentPositive, typedValue, true)
        colorPositive = typedValue.data
        context.theme.resolveAttribute(R.attr.percentNegative, typedValue, true)
        colorNegative = typedValue.data
    }

    inner class RatesViewHolder(private val binding: LiRateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            binding.symbolCrypto.text = item.getSymbol()
            binding.priceCurrency.text = priceFormatter.format(item.price())
            binding.pricePercent.text = changeFormatter.format(item.change24h())
            Picasso.get()
                .load(BuildConfig.IMG_ENDPOINT + item.getId() + ".png")
                .into(binding.iconCrypto)

            if (item.change24h() > 0) {
                binding.pricePercent.setTextColor(colorPositive)
            } else
                binding.pricePercent.setTextColor(colorNegative)

            OutlineCircle().apply(binding.iconCrypto)
        }
    }
}