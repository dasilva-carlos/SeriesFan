package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.ItemEpisodeBinding
import com.dasilva.carlos.seriesfan.databinding.ItemSeasonBinding
import com.dasilva.carlos.seriesfan.domain.EpisodeDiffCallback
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeItemVO
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeVO
import com.dasilva.carlos.seriesfan.domain.vo.SeasonVO
import com.dasilva.carlos.seriesfan.utils.inflateView
import java.lang.IllegalArgumentException

private const val SEASON_TYPE = 0
private const val EPISODE_TYPE = 1

class EpisodesAdapter : ListAdapter<EpisodeItemVO, RecyclerView.ViewHolder>(
    EpisodeDiffCallback()
) {
    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is EpisodeVO -> EPISODE_TYPE
        is SeasonVO -> SEASON_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        EPISODE_TYPE -> EpisodeViewHolder(parent.inflateView(R.layout.item_episode))
        SEASON_TYPE -> SeasonViewHolder(parent.inflateView(R.layout.item_season))
        else -> throw IllegalArgumentException("Illegal view type at ${this.javaClass}")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeViewHolder -> holder.bind(getItem(position))
            is SeasonViewHolder -> holder.bind(getItem(position))
        }
    }
}

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemEpisodeBinding.bind(view)

    fun bind(data: EpisodeItemVO) {
        if (data !is EpisodeVO) return
        binding.itemTitle.text = data.title
    }
}

class SeasonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSeasonBinding.bind(view)

    fun bind(data: EpisodeItemVO) {
        if (data !is SeasonVO) return
        binding.itemTitle.text = data.title
    }
}
