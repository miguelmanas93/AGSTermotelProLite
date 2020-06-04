package com.miguel.ags.agstermotelprolite.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.miguel.ags.agstermotelprolite.data.Result
import com.miguel.ags.agstermotelprolite.data.model.LoggedInUser
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.utils.Avisos
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository : KoinComponent {

    private val apiService: APIService by inject()
    private val mensajeEstado = MutableLiveData<Avisos<String>>()

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null

    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val signInInfo = Usuarios(0, username, password, emptyList())
            apiService.iniciarSesion(signInInfo).enqueue(object : Callback<Usuarios> {
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
                    if (response.code() == 200 || response.code() == 201) {
                        var editor : SharedPreferences.Editor? = null
                        editor?.putString("name", response.body()?.name)
                        editor?.putString("pass", response.body()?.pass)
                        editor?.commit()
                        mensajeEstado.value = Avisos( "Login success!")



                    } else if (response.code() == 500) {
                        mensajeEstado.value = Avisos("The given email or password is wrong!")
                    } else {
                        mensajeEstado.value = Avisos("Login failed!")
                    }
                }
            })
            val username = LoggedInUser(username)
            return Result.Success(username)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
