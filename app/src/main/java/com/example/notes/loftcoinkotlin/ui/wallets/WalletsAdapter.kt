package com.example.notes.loftcoinkotlin.ui.wallets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.databinding.LiWalletBinding

class WalletsAdapter : RecyclerView.Adapter<WalletsAdapter.WalletsViewHolder>() {

    lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletsViewHolder {
        return WalletsViewHolder(LiWalletBinding.inflate(inflater, parent, false).root)
    }

    override fun onBindViewHolder(holder: WalletsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }


    inner class WalletsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {

        }
    }
}