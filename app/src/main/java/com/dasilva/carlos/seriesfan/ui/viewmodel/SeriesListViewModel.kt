package com.dasilva.carlos.seriesfan.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dasilva.carlos.seriesfan.domain.FlowPagingDataSource
import com.dasilva.carlos.seriesfan.domain.vo.SeriesVO
import com.dasilva.carlos.seriesfan.network.api.ShowsApi
import com.dasilva.carlos.seriesfan.network.api.dto.ShowDTO

private const val DEFAULT_PAGE_SIZE = 250
private const val INITIAL_LOAD = 1
private const val PREFETCH_DISTANCE = 1

class SeriesListViewModel(
    private val api: ShowsApi
) : ViewModel() {
    val pagedDataSource: LiveData<PagingData<SeriesVO>> =
        Pager(getPagingConfig()) {
            FlowPagingDataSource(::convert, api::getShowsList)
        }.flow.cachedIn(viewModelScope).asLiveData(viewModelScope.coroutineContext)

    private fun convert(data: List<ShowDTO>): List<SeriesVO> = data.map {
        SeriesVO(it.id, it.name, it.image?.medium.orEmpty())
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = DEFAULT_PAGE_SIZE,
        initialLoadSize = INITIAL_LOAD,
        prefetchDistance = PREFETCH_DISTANCE
    )
}
