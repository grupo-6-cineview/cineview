package com.github.grupo6cineview.cineview.application

import android.app.Application
import com.github.grupo6cineview.cineview.di.AppComponent.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(allModules)
        }
    }
}