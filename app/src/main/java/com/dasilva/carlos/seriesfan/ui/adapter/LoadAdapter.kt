package com.dasilva.carlos.seriesfan.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.ItemLoadingBinding
import com.dasilva.carlos.seriesfan.utils.inflateView

class LoadAdapter(val onTryAgain: () -> Unit) : LoadStateAdapter<LoadAdapter.LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingViewHolder(parent.inflateView(R.layout.item_loading))

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemLoadingBinding.bind(view)

        fun bind(loadState: LoadState) {
            binding.loadingView.isVisible = loadState == LoadState.Loading
            binding.errorButton.isVisible = loadState is LoadState.Error
            binding.errorButton.setOnClickListener {
                onTryAgain()
            }
        }
    }
}
