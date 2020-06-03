package com.miguel.ags.agstermotelprolite.injection

import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.repository.UsuariosRespository
import org.koin.dsl.module

val DependencyModule = module{

    single<UsuariosRespository> {
        UsuariosRespository()
    }
}