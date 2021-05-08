package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dasilva.carlos.seriesfan.databinding.ItemSeriesBinding
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO

class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSeriesBinding.bind(view)

    fun bind(data: SeriesVO, listener: SeriesItemListener) {
        binding.root.setOnClickListener {
            listener.onItemClicked(data)
        }

        Glide.with(binding.root)
            .load(data.banner)
            .centerCrop()
            .into(binding.itemBanner)

        binding.itemTitle.text = data.title
    }
}

interface SeriesItemListener {
    fun onItemClicked(data: SeriesVO)
}
