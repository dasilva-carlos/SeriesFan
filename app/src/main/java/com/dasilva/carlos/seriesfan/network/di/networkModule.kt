package com.dasilva.carlos.seriesfan.network.di

import com.dasilva.carlos.seriesfan.network.api.ShowsApi
import com.dasilva.carlos.seriesfan.network.client.NetworkServiceProvider
import com.dasilva.carlos.seriesfan.network.flow.FlowAdapterFactory
import com.dasilva.carlos.seriesfan.network.interceptors.getLoggingInterceptor
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { FlowAdapterFactory() }
    single { GsonConverterFactory.create() }
    single { NetworkServiceProvider(get<FlowAdapterFactory>(), get<GsonConverterFactory>(), getLoggingInterceptor()) }

    factory { get<NetworkServiceProvider>().createService(ShowsApi::class.java) }
}
