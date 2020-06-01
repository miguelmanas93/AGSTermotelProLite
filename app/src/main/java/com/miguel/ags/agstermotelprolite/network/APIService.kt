package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*


interface APIService {

    // Peticion Iniciar Sesion
    @Headers("Content-Type:application/json")
    @POST("/usuarios")
    fun iniciarSesion(
        @Body info: Usuarios
    ): retrofit2.Call<Usuarios>

    @GET("usuarios")
     fun getUsuarios(): Call<List<Usuarios>>


}

