package com.dasilva.carlos.seriesfan.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateView(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}
