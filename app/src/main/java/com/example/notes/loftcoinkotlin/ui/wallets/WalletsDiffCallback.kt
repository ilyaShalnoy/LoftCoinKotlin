package com.example.notes.loftcoinkotlin.ui.wallets

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.loftcoinkotlin.data.wallets.Wallet

class WalletsDiffCallback : DiffUtil.ItemCallback<Wallet>() {

    override fun areItemsTheSame(oldItem: Wallet, newItem: Wallet): Boolean = oldItem.getUid() == newItem.getUid()

    override fun areContentsTheSame(oldItem: Wallet, newItem: Wallet): Boolean = oldItem == newItem

}