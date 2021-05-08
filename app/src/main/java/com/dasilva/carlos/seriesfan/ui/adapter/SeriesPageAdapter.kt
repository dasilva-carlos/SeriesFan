package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.domain.SeriesDiffCallback
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO
import com.dasilva.carlos.seriesfan.utils.inflateView

class SeriesPageAdapter(
    private val listener: SeriesItemListener
) : PagingDataAdapter<SeriesVO, SeriesViewHolder>(
    diffCallback = SeriesDiffCallback()
) {
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SeriesViewHolder(parent.inflateView(R.layout.item_series))
}
