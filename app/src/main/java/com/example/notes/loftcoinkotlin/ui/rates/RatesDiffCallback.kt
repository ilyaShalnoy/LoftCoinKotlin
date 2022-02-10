package com.example.notes.loftcoinkotlin.ui.rates

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.loftcoinkotlin.data.net.Coin

class RatesDiffCallback: DiffUtil.ItemCallback<Coin>() {

    override fun areItemsTheSame(oldItem: Coin, newItem: Coin) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin) = oldItem == newItem
}