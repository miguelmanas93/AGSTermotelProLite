package com.miguel.ags.agstermotelprolite.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miguel.ags.agstermotelprolite.MainActivity
import com.miguel.ags.agstermotelprolite.R
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var loginViewModel : LoginViewModel

    private var etEmailUsuario: EditText? = null
    private var etContrasenia: EditText? = null
    private var btLogin: Button? = null
    private var tvRegistro: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Cambiamos el nombre a la barra superior
        val actionBar = supportActionBar
        actionBar!!.title = "AGS - Login test"

        //Declaramos los elementos que vamos a tener en la pantalla
        etEmailUsuario = findViewById(R.id.etEmailUsuario)
        etContrasenia = findViewById(R.id.etContrasenia)
        btLogin = findViewById<Button>(R.id.btLogin)
        tvRegistro = findViewById<TextView>(R.id.tvRegistroNew)



        btLogin!!.setOnClickListener(this)
        tvRegistro!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btLogin -> {
                loginViewModel.iniciarSesion()
            }

        }
    }
}

