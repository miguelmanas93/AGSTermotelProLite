package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import retrofit2.Call
import retrofit2.http.*


interface APIService {

    // Peticion Iniciar Sesion
    @Headers("Content-Type:application/json")
    @POST("/usuarios")
    fun iniciarSesion(
        @Body info: Usuarios
    ): retrofit2.Call<Usuarios>

    @FormUrlEncoded
    @POST("usuarios?id={id}&name={name}&pass={pass}")
    fun userLogin(
        @Path("id") id: String,
        @Path("name") name: String,
        @Path("pass") pass: String
    ): retrofit2.Call<Usuarios>

    @GET("usuarios")
    open fun getUsuarios(): Call<List<Usuarios>>



}

