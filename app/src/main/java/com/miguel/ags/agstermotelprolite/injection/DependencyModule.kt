package com.miguel.ags.agstermotelprolite.injection

import com.miguel.ags.agstermotelprolite.ui.home.HomeViewModel
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.miguel.ags.agstermotelprolite.ui.gallery.GalleryViewModel
import com.miguel.ags.agstermotelprolite.ui.login.LoginViewModel
import org.koin.dsl.module

val DependencyModule = module{

    single { RetrofitClient().getInstance() }
    single { LoginViewModel(get()) }
    //single { GalleryViewModel()}
    //single { HomeViewModel() }

}





