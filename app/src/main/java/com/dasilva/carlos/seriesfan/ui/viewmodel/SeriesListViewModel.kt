package com.dasilva.carlos.seriesfan.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
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
import com.dasilva.carlos.seriesfan.network.api.dto.ShowRatingDTO
import com.dasilva.carlos.seriesfan.network.data.ResponseState
import com.dasilva.carlos.seriesfan.utils.mapToResponseState
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

private const val DEFAULT_PAGE_SIZE = 250
private const val INITIAL_LOAD = 1
private const val PREFETCH_DISTANCE = 1
private const val DEBOUNCE_SEARCH_PERIOD = 100L

class SeriesListViewModel(
    private val api: ShowsApi
) : ViewModel() {

    val pagedDataSource: LiveData<PagingData<SeriesVO>> =
        Pager(getPagingConfig()) {
            FlowPagingDataSource(::convert, api::getShowsList)
        }.flow.cachedIn(viewModelScope).asLiveData(viewModelScope.coroutineContext)

    private val _search = MutableLiveData<String>()

    val showSearchList: LiveData<Boolean> = _search.asFlow()
        .debounce(DEBOUNCE_SEARCH_PERIOD)
        .map { !it.isNullOrEmpty() }
        .distinctUntilChanged()
        .asLiveData(viewModelScope.coroutineContext)

    val searchList: LiveData<ResponseState<List<SeriesVO>>> = _search.asFlow()
        .filter { !it.isNullOrBlank() }
        .debounce(DEBOUNCE_SEARCH_PERIOD)
        .flatMapLatest {
            api.search(query = it).map(::convertRating).mapToResponseState()
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun search(query: String?) {
        _search.value = query.orEmpty()
    }

    private fun convert(data: List<ShowDTO>): List<SeriesVO> = data.map {
        SeriesVO(it.id, it.name, it.image?.medium.orEmpty())
    }

    private fun convertRating(data: List<ShowRatingDTO>): List<SeriesVO> = data.map {
        SeriesVO(it.show.id, it.show.name, it.show.image?.medium.orEmpty())
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = DEFAULT_PAGE_SIZE,
        initialLoadSize = INITIAL_LOAD,
        prefetchDistance = PREFETCH_DISTANCE
    )
}
