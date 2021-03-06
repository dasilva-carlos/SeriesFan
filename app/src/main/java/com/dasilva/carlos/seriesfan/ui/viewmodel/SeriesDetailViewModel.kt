package com.dasilva.carlos.seriesfan.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeDetailVO
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeItemVO
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeVO
import com.dasilva.carlos.seriesfan.domain.vo.SeasonVO
import com.dasilva.carlos.seriesfan.domain.vo.SeriesDetailVO
import com.dasilva.carlos.seriesfan.network.api.ShowsApi
import com.dasilva.carlos.seriesfan.network.api.dto.EpisodeDTO
import com.dasilva.carlos.seriesfan.network.api.dto.ShowDetailDTO
import com.dasilva.carlos.seriesfan.network.data.ResponseState
import com.dasilva.carlos.seriesfan.network.data.Success
import com.dasilva.carlos.seriesfan.utils.getSpannedFromHtml
import com.dasilva.carlos.seriesfan.utils.mapToResponseState
import com.dasilva.carlos.seriesfan.utils.reduceWithComma
import java.lang.ref.WeakReference
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class SeriesDetailViewModel(
    val api: ShowsApi,
    context: Context
) : ViewModel() {
    private val referenceContext = WeakReference(context)

    private val _seriesInformation = MutableLiveData<Int>()
    val seriesInformation: LiveData<ResponseState<SeriesDetailVO>> =
        _seriesInformation.asFlow().flatMapLatest {
            api.getShowDetails(it)
                .map(::convert)
                .mapToResponseState()
        }.asLiveData(viewModelScope.coroutineContext)

    fun fetchViewInformation(id: Int) {
        if (seriesInformation.value !is Success) {
            _seriesInformation.value = id
        }
    }

    private fun convert(data: ShowDetailDTO) = SeriesDetailVO(
        title = data.name,
        banner = data.image?.medium.orEmpty(),
        schedule = referenceContext.get()?.getString(
            R.string.schedule_data,
            data.schedule.days.reduceWithComma(),
            data.schedule.time
        ).orEmpty(),
        genres = data.genres.reduceWithComma(),
        resume = data.summary.getSpannedFromHtml(),
        episodes = data.embedded?.episodes?.convert() ?: listOf()
    )

    private fun List<EpisodeDTO>.convert(): List<EpisodeItemVO> {
        val list = mutableListOf<EpisodeItemVO>()
        var lastSeason = -1
        forEach {
            if (it.season != lastSeason) {
                list.add(
                    SeasonVO(referenceContext.get()?.getString(R.string.season_data, it.season).orEmpty())
                )
                lastSeason = it.season
            }

            list.add(
                EpisodeVO("${it.number} - ${it.name}", it.convert())
            )
        }
        return list
    }

    private fun EpisodeDTO.convert() = EpisodeDetailVO(
        name = name,
        description = referenceContext.get()?.getString(R.string.episode_data, season, number).orEmpty(),
        image = image?.original.orEmpty(),
        resume = summary.getSpannedFromHtml()
    )
}
