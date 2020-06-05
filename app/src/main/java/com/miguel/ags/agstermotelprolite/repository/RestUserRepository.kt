package com.miguel.ags.agstermotelprolite.repository

import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call

class RestUserRepository : UserRepository, KoinComponent {

    private val apiService: APIService by inject()

    override fun cargarDatosUsuarios() : Call<List<Usuarios>> {
       return apiService.getUsuarios()
    }

    override fun login(username: String, password: String) {
        TODO("Not yet implemented")
    }

}