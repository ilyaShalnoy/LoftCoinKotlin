package com.example.notes.loftcoinkotlin.ui.wallets

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.core.util.BalanceFormatter
import com.example.notes.loftcoinkotlin.core.util.PriceFormatter
import com.example.notes.loftcoinkotlin.data.Transaction
import com.example.notes.loftcoinkotlin.databinding.LiTransactionBinding
import javax.inject.Inject

class TransactionAdapter @Inject constructor(
    private val priceFormatter: PriceFormatter,
    private val balanceFormatter: BalanceFormatter
) : ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(LiTransactionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }


    inner class TransactionViewHolder(private val binding: LiTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val transaction = getItem(position)
            binding.transactionBalanceTextCrypto.text = priceFormatter.format(transaction.getAmount())
            val amountCurrency = transaction.getAmount() * transaction.getCoin().getPrice()
            binding.transactionBalanceTextCurrency.text = priceFormatter.format(transaction.getCoin().getCurrencyCode(), amountCurrency)
            binding.textDataTransaction.text = DateFormat.getDateFormat(inflater.context).format(transaction.getCreateAt())
        }
    }

    class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {

        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean = oldItem.getUid() == newItem.getUid()

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean = oldItem == newItem


    }
}