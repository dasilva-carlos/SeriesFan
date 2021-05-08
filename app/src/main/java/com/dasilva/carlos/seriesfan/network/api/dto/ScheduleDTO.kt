package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class ScheduleDTO(
    @SerializedName("time")
    val time: String,

    @SerializedName("days")
    val days: List<String>
)
