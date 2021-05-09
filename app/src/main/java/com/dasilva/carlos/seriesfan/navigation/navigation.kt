package com.dasilva.carlos.seriesfan.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeDetailVO
import com.dasilva.carlos.seriesfan.ui.view.EpisodeDetailFragment
import com.dasilva.carlos.seriesfan.ui.view.SeriesDetailFragment

fun Fragment.goToSeriesDetails(id: Int) =
    findNavController().navigate(R.id.action_to_seriesDetailFragment, SeriesDetailFragment.buildBundle(id))

fun Fragment.goToEpisodeDetails(data: EpisodeDetailVO) =
    findNavController().navigate(R.id.action_to_episodeDetailFragment, EpisodeDetailFragment.buildBundle(data))
