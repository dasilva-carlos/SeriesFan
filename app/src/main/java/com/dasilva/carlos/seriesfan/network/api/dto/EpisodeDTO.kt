package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("season")
    val season: Int,

    @SerializedName("number")
    val number: Int,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("image")
    val image: ImageDTO?
)
