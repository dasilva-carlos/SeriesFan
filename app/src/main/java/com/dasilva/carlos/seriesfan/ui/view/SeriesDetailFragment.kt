package com.dasilva.carlos.seriesfan.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.FragmentSeriesDetailBinding
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeDetailVO
import com.dasilva.carlos.seriesfan.domain.vo.SeriesDetailVO
import com.dasilva.carlos.seriesfan.navigation.goToEpisodeDetails
import com.dasilva.carlos.seriesfan.structure.BindingFragment
import com.dasilva.carlos.seriesfan.ui.adapter.EpisodeItemListener
import com.dasilva.carlos.seriesfan.ui.adapter.EpisodesAdapter
import com.dasilva.carlos.seriesfan.ui.viewmodel.SeriesDetailViewModel
import com.dasilva.carlos.seriesfan.utils.observeStates
import org.koin.android.viewmodel.ext.android.viewModel

class SeriesDetailFragment : BindingFragment<FragmentSeriesDetailBinding>(R.layout.fragment_series_detail), EpisodeItemListener {
    override val binder = FragmentSeriesDetailBinding::bind
    private val viewModel: SeriesDetailViewModel by viewModel()

    companion object {
        private const val SERIES_ID = "SERIES_ID"

        fun buildBundle(id: Int) = bundleOf(SERIES_ID to id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    private fun prepareView() {
        viewModel.getSeriesInformation(getSeriesId()).observeStates(
            viewLifecycleOwner,
            onLoading = ::onLoading,
            onSuccess = ::onSuccess
        )
    }

    private fun onLoading() {
        binding.loadingView.isVisible = true
        binding.detailsScroll.isVisible = false
    }

    private fun onSuccess(data: SeriesDetailVO) {
        binding.run {
            loadingView.isVisible = false
            detailsScroll.isVisible = true

            title.text = data.title

            Glide.with(root)
                .load(data.banner)
                .into(banner)

            scheduleText.text = data.schedule
            genreText.text = data.genres
            resumeText.text = data.resume

            episodesRecyclerView.adapter = EpisodesAdapter(this@SeriesDetailFragment).apply {
                submitList(data.episodes)
            }
        }
    }

    private fun getSeriesId() = requireArguments().getInt(SERIES_ID)

    override fun onItemClicked(data: EpisodeDetailVO) {
        goToEpisodeDetails(data)
    }
}
