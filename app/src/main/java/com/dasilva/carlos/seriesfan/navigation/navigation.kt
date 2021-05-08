package com.dasilva.carlos.seriesfan.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.ui.view.SeriesDetailFragment

fun Fragment.goToDetails(id: Int) =
    findNavController().navigate(R.id.action_to_seriesDetailFragment, SeriesDetailFragment.buildBundle(id))
