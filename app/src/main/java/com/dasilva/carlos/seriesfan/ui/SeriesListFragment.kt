package com.dasilva.carlos.seriesfan.ui

import android.os.Bundle
import android.view.View
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.FragmentSeriesListBinding
import com.dasilva.carlos.seriesfan.structure.BindingFragment

class SeriesListFragment : BindingFragment<FragmentSeriesListBinding>(R.layout.fragment_series_list) {

    override val binder = FragmentSeriesListBinding::bind

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerView
    }
}
