package com.miguel.ags.agstermotelprolite.ui.login

//import com.miguel.ags.agstermotelprolite.data.LoginRepository

import android.content.SharedPreferences
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.R
import com.miguel.ags.agstermotelprolite.data.model.LoginRepository
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.miguel.ags.agstermotelprolite.utils.Avisos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import com.miguel.ags.agstermotelprolite.data.Result

//class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

class LoginViewModel (private val loginRepository: LoginRepository) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    private val mensajeEstado = MutableLiveData<Avisos<String>>()

    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)
        val signInInfo = Usuarios(0, username, password)

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
                if (response.code() == 200 || response.code() == 201) {
                    var editor : SharedPreferences.Editor? = null
                    editor?.putString("id", "0")
                    editor?.putString("name", response.body()?.name)
                    editor?.putString("pass", response.body()?.pass)
                    editor?.commit()
                    mensajeEstado.value = Avisos( "Login success!")

                    val result = loginRepository.login(username, password)

                    if (result is Result.Success) {
                        _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
                    } else {
                        _loginResult.value = LoginResult(error = R.string.login_failed)
                    }


                } else if (response.code() == 500) {
                    mensajeEstado.value = Avisos("The given email or password is wrong!")
                } else {
                    mensajeEstado.value = Avisos("Login failed!")
                }
            }
        })


    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
