package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class ShowDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: ImageDTO?,
)