package com.dasilva.carlos.seriesfan.domain.vo

import android.text.Spanned

class SeriesDetailVO(
    val title: String,
    val banner: String,
    val schedule: String,
    val genres: String,
    val resume: Spanned,
    val episodes: List<EpisodeItemVO>
)
