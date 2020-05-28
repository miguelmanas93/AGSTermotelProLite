package com.miguel.ags.agstermotelprolite.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.LoginResponse
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    fun iniciarSesion() {

        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            editTextEmail.error = "Email required"
            editTextEmail.requestFocus()
            return@setOnClickListener
        }


        if (password.isEmpty()) {
            editTextPassword.error = "Password required"
            editTextPassword.requestFocus()
            return@setOnClickListener
        }

        RetrofitClient.instance.userLogin(email, password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (!response.body()?.error!!) {

                        SharedPrefManager.getInstance(applicationContext)
                            .saveUser(response.body()?.user!!)

                        val intent = Intent(applicationContext, ProfileActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)


                    } else {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            })

    }
}
