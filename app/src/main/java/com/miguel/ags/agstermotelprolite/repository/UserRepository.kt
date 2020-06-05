package com.miguel.ags.agstermotelprolite.repository

import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import org.koin.core.KoinComponent
import retrofit2.Call

interface UserRepository : KoinComponent{

    fun cargarDatosUsuarios() : Call<List<Usuarios>>
    fun login(username : String, password : String)

}