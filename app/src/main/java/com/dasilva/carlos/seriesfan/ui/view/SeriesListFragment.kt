package com.dasilva.carlos.seriesfan.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.FragmentSeriesListBinding
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO
import com.dasilva.carlos.seriesfan.navigation.goToDetails
import com.dasilva.carlos.seriesfan.structure.BindingFragment
import com.dasilva.carlos.seriesfan.ui.adapter.LoadAdapter
import com.dasilva.carlos.seriesfan.ui.adapter.SeriesAdapter
import com.dasilva.carlos.seriesfan.ui.adapter.SeriesItemListener
import com.dasilva.carlos.seriesfan.ui.adapter.SeriesPageAdapter
import com.dasilva.carlos.seriesfan.ui.viewmodel.SeriesListViewModel
import com.dasilva.carlos.seriesfan.utils.observeStates
import org.koin.android.viewmodel.ext.android.viewModel

private const val FOOTER_VIEW_TYPE = 1
private const val DEFAULT_LOOK_UP_SIZE = 1

class SeriesListFragment : BindingFragment<FragmentSeriesListBinding>(R.layout.fragment_series_list), SeriesItemListener {

    override val binder = FragmentSeriesListBinding::bind

    private val viewModel: SeriesListViewModel by viewModel()
    private lateinit var pageAdapter: SeriesPageAdapter
    private lateinit var listAdapter: ConcatAdapter
    private lateinit var searchAdapter: SeriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePagedList()
        prepareSearch()
    }

    private fun preparePagedList() {
        pageAdapter = SeriesPageAdapter(this)
        listAdapter = pageAdapter.withLoadStateFooter(LoadAdapter())

        binding.run {
            recyclerView.adapter = listAdapter
            recyclerView.getGridLayout()?.spanSizeLookup = getSpanSizeLookup()
        }

        viewModel.pagedDataSource.observe(viewLifecycleOwner) { data ->
            pageAdapter.submitData(lifecycle, data)
        }
        pageAdapter.addLoadStateListener {
            binding.loadingView.isVisible = (it.refresh == LoadState.Loading && pageAdapter.itemCount == 0)
        }
    }

    private fun prepareSearch() {
        binding.editText.addTextChangedListener {
            viewModel.search(it?.toString())
        }

        searchAdapter = SeriesAdapter(this)

        viewModel.showSearchList.observe(viewLifecycleOwner) { showSearchList ->
            binding.recyclerView.adapter = if (showSearchList) {
                searchAdapter.submitList(null)
                searchAdapter
            } else {
                listAdapter
            }
        }

        viewModel.searchList.observeStates(
            viewLifecycleOwner,
            onLoading = ::onSearchLoading,
            onSuccess = ::onSearchSuccess
        )
    }

    private fun onSearchLoading() {
        binding.loadingView.isVisible = searchAdapter.itemCount == 0
    }

    private fun onSearchSuccess(data: List<SeriesVO>) {
        binding.run {
            loadingView.isVisible = false
            searchAdapter.submitList(data)
        }
    }

    private fun getSpanSizeLookup() = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (listAdapter.getItemViewType(position) == FOOTER_VIEW_TYPE) {
                resources.getInteger(R.integer.span_size_series_list)
            } else {
                DEFAULT_LOOK_UP_SIZE
            }
        }
    }

    private fun RecyclerView.getGridLayout() = layoutManager as? GridLayoutManager

    override fun onItemClicked(data: SeriesVO) {
        goToDetails(data.id)
    }
}
