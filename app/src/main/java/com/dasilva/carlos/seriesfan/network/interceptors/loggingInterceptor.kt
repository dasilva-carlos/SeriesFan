package com.dasilva.carlos.seriesfan.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

fun getLoggingInterceptor() = HttpLoggingInterceptor { message ->
    Timber.tag(
        "OkHttp"
    ).d(message)
}