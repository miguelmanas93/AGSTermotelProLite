package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import retrofit2.Call
import retrofit2.http.*


interface APIService {

    @Headers("Content-Type:application/json")
    // Peticion Iniciar Sesion
    @POST("/usuarios")
    fun iniciarSesion(
        @Body info: Usuarios
    ): retrofit2.Call<Usuarios>

    @GET("/usuarios")
     fun getUsuarios(): Call<List<Usuarios>>

    @GET("/usuarios?id=0&name=Miguel&pass=qwerty")
    fun getCamaras(): Call<List<Camaras>>

    @GET("/usuarios")
    fun getSondas(@Query("id") id : Int, @Query("nombre") nombre : String): Call<ArrayList<Sondas>>


}

