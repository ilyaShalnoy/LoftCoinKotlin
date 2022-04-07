package com.example.notes.loftcoinkotlin.ui.wallets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.BuildConfig
import com.example.notes.loftcoinkotlin.core.util.BalanceFormatter
import com.example.notes.loftcoinkotlin.core.util.ImageLoader
import com.example.notes.loftcoinkotlin.core.util.OutlineCircle
import com.example.notes.loftcoinkotlin.core.util.PriceFormatter
import com.example.notes.loftcoinkotlin.data.wallets.Wallet
import com.example.notes.loftcoinkotlin.databinding.LiWalletBinding
import javax.inject.Inject

class WalletsAdapter @Inject constructor(
    private val priceFormatter: PriceFormatter,
    private val balanceFormatter: BalanceFormatter,
    private val imageLoader: ImageLoader
) : ListAdapter<Wallet, WalletsAdapter.WalletsViewHolder>(WalletsDiffCallback()) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletsViewHolder {
        return WalletsViewHolder(LiWalletBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: WalletsViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }


    inner class WalletsViewHolder(private val binding: LiWalletBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) {
            val wallet = getItem(position)
            binding.titleCryptoCard.text = wallet.getCoin().getSymbol()
            binding.balanceTextCrypto.text = balanceFormatter.format(wallet)
            val balance = wallet.getBalance() * wallet.getCoin().getPrice()
            binding.balanceTextCurrency.text = priceFormatter.format(wallet.getCoin().getCurrencyCode(), balance)
            imageLoader
                .load(BuildConfig.IMG_ENDPOINT + wallet.getCoin().getId() + ".png")
                .into(binding.logoCryptoCard)

            OutlineCircle().apply(binding.logoCryptoCard)
        }
    }
}