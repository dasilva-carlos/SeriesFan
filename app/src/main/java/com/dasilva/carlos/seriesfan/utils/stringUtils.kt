package com.dasilva.carlos.seriesfan.utils

import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT

fun List<String>.reduceWithComma() = if (isNotEmpty()) {
    reduceRight { text1, text2 -> "$text1, $text2" }
} else { "-" }

fun String.getSpannedFromHtml() = HtmlCompat.fromHtml(this, FROM_HTML_MODE_COMPACT)
