package com.dasilva.carlos.seriesfan.domain

import androidx.recyclerview.widget.DiffUtil
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeItemVO

class EpisodeDiffCallback : DiffUtil.ItemCallback<EpisodeItemVO>() {
    override fun areItemsTheSame(oldItem: EpisodeItemVO, newItem: EpisodeItemVO): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: EpisodeItemVO, newItem: EpisodeItemVO): Boolean =
        oldItem == newItem
}
