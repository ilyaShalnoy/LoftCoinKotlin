package com.example.notes.loftcoinkotlin.ui.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.data.net.Coin
import com.example.notes.loftcoinkotlin.databinding.LiRateBinding
import com.example.notes.loftcoinkotlin.ui.rates.RatesAdapter.*

class RatesAdapter : ListAdapter<Coin, RatesViewHolder>(RatesDiffCallback()) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(LiRateBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    inner class RatesViewHolder(private val binding: LiRateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.symbolCrypto.text = getItem(position).symbol
        }

    }

}