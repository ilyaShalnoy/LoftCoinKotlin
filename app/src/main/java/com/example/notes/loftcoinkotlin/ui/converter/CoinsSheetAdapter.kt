package com.example.notes.loftcoinkotlin.ui.converter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.BuildConfig
import com.example.notes.loftcoinkotlin.core.util.ImageLoader
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import com.example.notes.loftcoinkotlin.data.currency.Currency
import com.example.notes.loftcoinkotlin.data.database.CacheCoin
import com.example.notes.loftcoinkotlin.databinding.LiSheetDialogBinding
import com.example.notes.loftcoinkotlin.ui.converter.CoinsSheetAdapter.*
import javax.inject.Inject

class CoinsSheetAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    ) : ListAdapter<CoinsDataModel, CoinsSheetViewHolder>(DiffCallback()) {

    private lateinit var inflater: LayoutInflater

    interface OnItemCoinsSheetClickListener {
        fun onItemClick(coin: CoinsDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsSheetViewHolder {
      return CoinsSheetViewHolder(LiSheetDialogBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CoinsSheetViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        inflater = LayoutInflater.from(recyclerView.context)
    }

    inner class CoinsSheetViewHolder(
        private val binding: LiSheetDialogBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            binding.name.text = item.getName()
            imageLoader
                .load(BuildConfig.IMG_ENDPOINT + item.getId() + ".png")
                .into(binding.logo)
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<CoinsDataModel>() {
        override fun areItemsTheSame(oldItem: CoinsDataModel, newItem: CoinsDataModel): Boolean =
            oldItem.getId() == newItem.getId()

        override fun areContentsTheSame(oldItem: CoinsDataModel, newItem: CoinsDataModel): Boolean =
            oldItem == newItem
    }
}