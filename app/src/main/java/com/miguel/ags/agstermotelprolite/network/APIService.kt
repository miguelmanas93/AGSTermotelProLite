package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.data.model.LoginResponse
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    //users?id=1&name=NombreUsuario&pass=ContraseniaUsuario
    @GET("/users")
    fun getTodosDatos(@Query("id") id : String)


    @FormUrlEncoded
    @POST("/usuarios")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<LoginResponse>
}

