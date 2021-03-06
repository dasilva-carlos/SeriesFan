package com.dasilva.carlos.seriesfan.ui.di

import com.dasilva.carlos.seriesfan.ui.viewmodel.SeriesDetailViewModel
import com.dasilva.carlos.seriesfan.ui.viewmodel.SeriesListViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { SeriesListViewModel(get()) }
    factory { SeriesDetailViewModel(get(), get()) }
}
