package com.miguel.ags.agstermotelprolite.injection


import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.miguel.ags.agstermotelprolite.repository.LoginRepository
import com.miguel.ags.agstermotelprolite.ui.login.LoginActivity
import org.koin.dsl.module

val DependencyModule = module {
    single<APIService> {
        RetrofitClient().getInstance()
    }
    single {
        LoginRepository()
    }
}






