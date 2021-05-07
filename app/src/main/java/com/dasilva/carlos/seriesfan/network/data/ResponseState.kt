package com.dasilva.carlos.seriesfan.network.data

sealed class ResponseState<out T : Any>

object Loading : ResponseState<Nothing>()

class Success<T : Any>(
    val data: T
) : ResponseState<T>()

class Fail(
    val throwable: Throwable
) : ResponseState<Nothing>()
