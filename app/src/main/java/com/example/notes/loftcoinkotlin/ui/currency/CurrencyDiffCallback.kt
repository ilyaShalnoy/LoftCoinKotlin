package com.example.notes.loftcoinkotlin.ui.currency

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.loftcoinkotlin.data.Currency

class CurrencyDiffCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency) = oldItem == newItem
}