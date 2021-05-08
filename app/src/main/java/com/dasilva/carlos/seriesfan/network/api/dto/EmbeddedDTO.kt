package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class EmbeddedDTO(
    @SerializedName("episodes")
    val episodes: List<EpisodeDTO>?
)
