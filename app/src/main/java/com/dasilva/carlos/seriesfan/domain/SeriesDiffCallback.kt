package com.dasilva.carlos.seriesfan.domain

import androidx.recyclerview.widget.DiffUtil
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO

class SeriesDiffCallback : DiffUtil.ItemCallback<SeriesVO>() {
    override fun areItemsTheSame(oldItem: SeriesVO, newItem: SeriesVO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SeriesVO, newItem: SeriesVO): Boolean =
        oldItem == newItem
}
