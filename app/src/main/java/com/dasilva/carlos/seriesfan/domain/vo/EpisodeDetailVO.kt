package com.dasilva.carlos.seriesfan.domain.vo

import android.text.Spanned
import java.io.Serializable

class EpisodeDetailVO(
    val name: String,
    val description: String,
    val image: String,
    val resume: Spanned
) : Serializable
