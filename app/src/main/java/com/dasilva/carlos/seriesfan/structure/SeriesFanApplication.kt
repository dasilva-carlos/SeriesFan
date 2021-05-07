package com.dasilva.carlos.seriesfan.structure

import android.app.Application
import com.dasilva.carlos.seriesfan.BuildConfig
import com.dasilva.carlos.seriesfan.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class SeriesFanApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SeriesFanApplication)

            if (BuildConfig.DEBUG) {
                androidLogger(level = Level.DEBUG)
            }

            modules(getModules())
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun getModules() = listOf(
        networkModule
    )
}
