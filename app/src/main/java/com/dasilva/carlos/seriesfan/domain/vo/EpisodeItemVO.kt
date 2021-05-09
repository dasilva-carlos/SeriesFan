package com.dasilva.carlos.seriesfan.domain.vo

import com.dasilva.carlos.seriesfan.network.api.dto.EpisodeDTO

sealed class EpisodeItemVO

data class SeasonVO(val title: String) : EpisodeItemVO()

data class EpisodeVO(
    val title: String,
    val episodeData: EpisodeDTO
) : EpisodeItemVO()
