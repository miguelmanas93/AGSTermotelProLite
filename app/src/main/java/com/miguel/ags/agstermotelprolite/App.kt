package com.miguel.ags.agstermotelprolite

import android.app.Application
import com.miguel.ags.agstermotelprolite.injection.DependencyModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(DependencyModule)
        }
    }

}