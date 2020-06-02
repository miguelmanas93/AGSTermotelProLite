package com.miguel.ags.agstermotelprolite.data

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.miguel.ags.agstermotelprolite.data.model.LoggedInUser
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.miguel.ags.agstermotelprolite.utils.Avisos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val mensajeEstado = MutableLiveData<Avisos<String>>()

    fun login(username: String, password: String): Result<LoggedInUser> {


        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)
        val signInInfo = Usuarios(0, username, password, camaras = null)
        purApp.iniciarSesion(signInInfo).enqueue(object : Callback<Usuarios> {

            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                if (t.cause is ConnectException) {
                    mensajeEstado.value = Avisos("Check your connection!")
                } else {
                    mensajeEstado.value = Avisos("Something bad happened!")
                }
            }

            override fun onResponse(
                call: Call<Usuarios>,
                response: Response<Usuarios>
            ) {
                if (response.code() == 200) {
                    var editor: SharedPreferences.Editor? = null
                    editor?.putString("id", "0")
                    editor?.putString("name", response.body()?.name)
                    editor?.putString("pass", response.body()?.pass)
                   editor?.commit()

                    mensajeEstado.value = Avisos("Login success!")

                } else if (response.code() == 500) {
                    mensajeEstado.value = Avisos("The given email or password is wrong!")
                } else {
                    mensajeEstado.value = Avisos("Login failed!")
                }
            }
        })

        val userName = LoggedInUser("$username")
        return Result.Success(userName)
    }



    fun logout() {
        // TODO: revoke authentication
    }

}


