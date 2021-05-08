package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class ShowDetailDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("genres")
    val genres: List<String>,

    @SerializedName("schedule")
    val schedule: ScheduleDTO,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("image")
    val image: ImageDTO?,

    @SerializedName("_embedded")
    val embedded: EmbeddedDTO?
)
