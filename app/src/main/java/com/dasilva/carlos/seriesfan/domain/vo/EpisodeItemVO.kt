package com.dasilva.carlos.seriesfan.domain.vo

sealed class EpisodeItemVO

data class SeasonVO(val title: String) : EpisodeItemVO()

data class EpisodeVO(
    val title: String,
    val episodeData: EpisodeDetailVO
) : EpisodeItemVO()
