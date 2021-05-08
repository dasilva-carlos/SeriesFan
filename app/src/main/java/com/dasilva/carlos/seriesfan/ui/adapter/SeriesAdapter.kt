package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.domain.SeriesDiffCallback
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO
import com.dasilva.carlos.seriesfan.utils.inflateView

class SeriesAdapter : ListAdapter<SeriesVO, SeriesViewHolder>(
    SeriesDiffCallback()
) {
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SeriesViewHolder(parent.inflateView(R.layout.item_series))
}
