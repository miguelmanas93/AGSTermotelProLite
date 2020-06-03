package com.miguel.ags.agstermotelprolite

import android.app.Application
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyAplication : Application() {

    var listofModules = module {
        single { RetrofitClient() }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyAplication)
            modules(listofModules)
        }
    }

}