package com.dasilva.carlos.seriesfan.structure

import android.app.Application
import com.dasilva.carlos.seriesfan.BuildConfig
import com.dasilva.carlos.seriesfan.network.di.networkModule
import com.dasilva.carlos.seriesfan.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class SeriesFanApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidContext(this@SeriesFanApplication)

            if (BuildConfig.DEBUG) {
                androidLogger(level = Level.DEBUG)
            }

            modules(getModules())
        }
    }

    private fun getModules() = listOf(
        networkModule,
        uiModule
    )
}
