package com.example.notes.loftcoinkotlin.ui.currency

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.data.currency.Currency
import com.example.notes.loftcoinkotlin.databinding.LiCurrencyBinding

class CurrencyDialogAdapter(private val onItemDialogClickListener: OnItemDialogClickListener) :
    ListAdapter<Currency, CurrencyDialogAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {

    private lateinit var inflater: LayoutInflater

    interface OnItemDialogClickListener {
        fun onItemClick(currency: Currency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LiCurrencyBinding.inflate(inflater, parent, false), onItemDialogClickListener)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        inflater = LayoutInflater.from(recyclerView.context)
    }

    inner class CurrencyViewHolder(
        private val binding: LiCurrencyBinding,
        private val onItemDialogClickListener: OnItemDialogClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val item = getItem(position)
            binding.dialogSymbol.text = item.getSymbol()
            binding.dialogTitle.text = "${item.getCode()} | ${item.getTitle()}"
            itemView.setOnClickListener {
                onItemDialogClickListener.onItemClick(getItem(position))
            }
        }
    }
}