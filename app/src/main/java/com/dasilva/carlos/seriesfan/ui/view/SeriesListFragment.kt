package com.dasilva.carlos.seriesfan.ui.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.FragmentSeriesListBinding
import com.dasilva.carlos.seriesfan.structure.BindingFragment
import com.dasilva.carlos.seriesfan.ui.adapter.LoadAdapter
import com.dasilva.carlos.seriesfan.ui.adapter.SeriesPageAdapter
import com.dasilva.carlos.seriesfan.ui.viewmodel.SeriesListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val FOOTER_VIEW_TYPE = 1
private const val DEFAULT_LOOK_UP_SIZE = 1

class SeriesListFragment : BindingFragment<FragmentSeriesListBinding>(R.layout.fragment_series_list) {

    override val binder = FragmentSeriesListBinding::bind

    private val viewModel: SeriesListViewModel by viewModel()
    private lateinit var dataAdapter: SeriesPageAdapter
    private lateinit var recyclerViewAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    private fun prepareView() {
        dataAdapter = SeriesPageAdapter()
        recyclerViewAdapter = dataAdapter.withLoadStateFooter(LoadAdapter())

        binding.run {
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.getGridLayout()?.spanSizeLookup = getSpanSizeLookup()
        }

        viewModel.pagedDataSource.observe(viewLifecycleOwner) { data ->
            dataAdapter.submitData(lifecycle, data)
        }
    }

    private fun getSpanSizeLookup() = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (recyclerViewAdapter.getItemViewType(position) == FOOTER_VIEW_TYPE) {
                resources.getInteger(R.integer.span_size_series_list)
            } else {
                DEFAULT_LOOK_UP_SIZE
            }
        }
    }

    private fun RecyclerView.getGridLayout() = layoutManager as? GridLayoutManager
}
