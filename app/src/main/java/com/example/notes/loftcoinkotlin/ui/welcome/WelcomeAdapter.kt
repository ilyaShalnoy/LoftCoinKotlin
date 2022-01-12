package com.example.notes.loftcoinkotlin.ui.welcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.databinding.WelcomePageBinding

class WelcomeAdapter : RecyclerView.Adapter<WelcomeAdapter.WelcomeViewHolder>() {

    private lateinit var inflater: LayoutInflater

    private val IMG = arrayOf(
        R.drawable.welcome_page_1,
        R.drawable.welcome_page_2,
        R.drawable.welcome_page_3
    )

    private val TITLES = arrayOf(
        R.string.welcome_page_1_title,
        R.string.welcome_page_2_title,
        R.string.welcome_page_3_title
    )

    private val SUBTITLES = arrayOf(
        R.string.welcome_page_1_subtitle,
        R.string.welcome_page_2_subtitle,
        R.string.welcome_page_3_subtitle
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelcomeViewHolder {
        return WelcomeViewHolder(WelcomePageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: WelcomeViewHolder, position: Int) {
        holder.binding.image.setImageResource(IMG[position])
        holder.binding.title.setText(TITLES[position])
        holder.binding.subtitle.setText(SUBTITLES[position])
    }

    override fun getItemCount(): Int {
        return TITLES.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }


    class WelcomeViewHolder(val binding: WelcomePageBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}

