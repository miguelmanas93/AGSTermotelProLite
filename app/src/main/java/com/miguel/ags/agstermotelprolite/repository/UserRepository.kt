package com.miguel.ags.agstermotelprolite.repository

import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import retrofit2.Call

interface UserRepository {

    fun cargarDatosUsuarios() : Call<List<Usuarios>>
    fun login(username : String, password : String)

}