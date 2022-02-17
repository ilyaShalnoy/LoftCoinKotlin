package com.example.notes.loftcoinkotlin.ui.currency

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.data.Currency
import com.example.notes.loftcoinkotlin.databinding.LiCurrencyBinding

class CurrencyDialogAdapter: ListAdapter<Currency, CurrencyDialogAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LiCurrencyBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        inflater = LayoutInflater.from(recyclerView.context)
    }



    inner class CurrencyViewHolder(private val binding: LiCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            binding.dialogSymbol.text = item.getSymbol()
            binding.dialogTitle.text = "${item.getCode()} | ${item.getTitle()}"
        }

    }
}