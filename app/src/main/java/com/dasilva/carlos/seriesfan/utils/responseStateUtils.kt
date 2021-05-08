package com.dasilva.carlos.seriesfan.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dasilva.carlos.seriesfan.network.data.Fail
import com.dasilva.carlos.seriesfan.network.data.Loading
import com.dasilva.carlos.seriesfan.network.data.ResponseState
import com.dasilva.carlos.seriesfan.network.data.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

fun <T : Any> Flow<T>.mapToResponseState(): Flow<ResponseState<T>> = merge(flowOf(Loading), map {
    Success(it)
}.catch { e ->
    Fail(e)
})

fun <T : Any> LiveData<ResponseState<T>>.observeStates(
    owner: LifecycleOwner,
    onLoading: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null
) = observe(owner, Observer {
    when (it) {
        Loading -> onLoading?.invoke()
        is Success -> onSuccess?.invoke(it.data)
        is Fail -> onError?.invoke(it.throwable)
    }
})
