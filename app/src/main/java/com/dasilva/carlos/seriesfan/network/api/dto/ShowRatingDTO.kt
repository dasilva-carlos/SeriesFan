package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class ShowRatingDTO(
    @SerializedName("score")
    val score: Float,

    @SerializedName("show")
    val show: ShowDTO
)