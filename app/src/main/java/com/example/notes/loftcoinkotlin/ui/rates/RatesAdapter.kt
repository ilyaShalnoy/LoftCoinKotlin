package com.example.notes.loftcoinkotlin.ui.rates

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.BuildConfig
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.util.*
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import com.example.notes.loftcoinkotlin.databinding.LiRateBinding
import com.example.notes.loftcoinkotlin.ui.rates.RatesAdapter.RatesViewHolder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class RatesAdapter @Inject constructor(
    private val priceFormatter: PriceFormatter,
    private val changeFormatter: ChangeFormatter,
    private val imageLoader: ImageLoader
) : ListAdapter<CoinsDataModel, RatesViewHolder>(RatesDiffCallback()) {

    private lateinit var inflater: LayoutInflater
    private var colorNegative = Color.RED
    private var colorPositive = Color.GREEN

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(LiRateBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder,position)
        } else {
            holder.bindPayloads(payloads[0] as CoinsDataModel)
        }
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
            binding.priceCurrency.text = priceFormatter.format(item.getCurrencyCode(), item.getPrice())
            binding.pricePercent.text = changeFormatter.format(item.getChange())

            imageLoader
                .load(BuildConfig.IMG_ENDPOINT + item.getId() + ".png")
                .into(binding.iconCrypto)

            if (item.getChange() > 0) {
                binding.pricePercent.setTextColor(colorPositive)
            } else
                binding.pricePercent.setTextColor(colorNegative)

            OutlineCircle().apply(binding.iconCrypto)
        }

        fun bindPayloads(coin: CoinsDataModel) {
            binding.priceCurrency.text = priceFormatter.format(coin.getCurrencyCode(), coin.getPrice())
            binding.pricePercent.text = changeFormatter.format(coin.getChange())
        }
    }
}