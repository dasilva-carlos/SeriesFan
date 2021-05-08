package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.utils.inflateView

class LoadAdapter : LoadStateAdapter<LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingViewHolder(parent.inflateView(R.layout.item_loading))
}

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(loadState: LoadState) {
        itemView.isVisible = loadState == LoadState.Loading
    }
}
