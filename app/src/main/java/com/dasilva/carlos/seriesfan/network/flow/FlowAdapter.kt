package com.dasilva.carlos.seriesfan.network.flow

import com.dasilva.carlos.seriesfan.network.data.ResponseException
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

class FlowAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {
    override fun adapt(call: Call<T>): Flow<T> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            continuation.resumeWithException(t)
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            if (response.isSuccessful) {
                                try {
                                    continuation.resume(response.body())
                                } catch (e: Exception) {
                                    continuation.resumeWithException(e)
                                }
                            } else {
                                val e = ResponseException(response.code(), response.errorBody()?.string())
                                continuation.resumeWithException(e)
                            }
                        }
                    })
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }

    override fun responseType() = responseType
}
