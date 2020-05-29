package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    //usuarios?id=0&name=Miguel&pass=pass12Jukes
    @GET("/usuarios")
    fun getUsuario(@Query("id") id: String)

    @FormUrlEncoded
    @POST("usuarios/{name}{pass}")
    fun userLogin(
        @Field("name") name: String,
        @Field("pass") pass: String
    ): Call<LoginResponse>
}

