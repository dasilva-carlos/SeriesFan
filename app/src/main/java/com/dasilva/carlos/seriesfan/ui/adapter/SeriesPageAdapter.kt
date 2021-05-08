package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.ItemSeriesBinding
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO
import com.dasilva.carlos.seriesfan.ui.utils.inflateView

class SeriesPageAdapter : PagingDataAdapter<SeriesVO, SeriesViewHolder>(
    diffCallback = SeriesDiffCallback()
) {
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SeriesViewHolder(parent.inflateView(R.layout.item_series))
}

class SeriesDiffCallback : DiffUtil.ItemCallback<SeriesVO>() {
    override fun areItemsTheSame(oldItem: SeriesVO, newItem: SeriesVO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SeriesVO, newItem: SeriesVO): Boolean =
        oldItem == newItem
}

class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSeriesBinding.bind(view)

    fun bind(data: SeriesVO) {
        Glide.with(binding.root)
            .load(data.banner)
            .centerCrop()
            .into(binding.itemBanner)

        binding.itemTitle.text = data.title
    }
}
