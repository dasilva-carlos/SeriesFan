package com.dasilva.carlos.seriesfan.network.data

class ResponseException(
    val code: Int,
    val response: String?
) : Throwable()
