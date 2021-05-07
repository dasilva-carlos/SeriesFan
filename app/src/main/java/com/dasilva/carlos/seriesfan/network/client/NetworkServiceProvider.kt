package com.dasilva.carlos.seriesfan.network.client

import com.dasilva.carlos.seriesfan.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

private const val BASE_URL = "http://api.tvmaze.com"

class NetworkServiceProvider(
    adapterFactory: CallAdapter.Factory,
    converterFactory: Converter.Factory,
    logInterceptor: Interceptor
) {

    private val retrofit: Retrofit

    init {
        val client = OkHttpClient()
            .newBuilder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(logInterceptor)
                }
            }
            .addInterceptor(logInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }

    fun <T> createService(from: Class<T>): T = retrofit.create(from)
}
