package com.example.notes.loftcoinkotlin.ui.rates

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import com.example.notes.loftcoinkotlin.data.net.NetworkCoin

class RatesDiffCallback : DiffUtil.ItemCallback<CoinsDataModel>() {

    override fun areItemsTheSame(oldItem: CoinsDataModel, newItem: CoinsDataModel) = oldItem.getId() == newItem.getId()

    override fun areContentsTheSame(oldItem: CoinsDataModel, newItem: CoinsDataModel) = oldItem == newItem

    override fun getChangePayload(oldItem: CoinsDataModel, newItem: CoinsDataModel): Any {
        return newItem
    }
}