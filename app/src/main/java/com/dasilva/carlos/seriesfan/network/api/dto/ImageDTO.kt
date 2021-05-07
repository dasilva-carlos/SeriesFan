package com.dasilva.carlos.seriesfan.network.api.dto

import com.google.gson.annotations.SerializedName

data class ImageDTO(
    @SerializedName("medium")
    val medium: String?,

    @SerializedName("original")
    val original: String?
)
