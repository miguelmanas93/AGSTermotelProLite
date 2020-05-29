package com.miguel.ags.agstermotelprolite.ui.home

import android.util.Log
import android.widget.Spinner
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SpinnerViewModel : ViewModel() {
    lateinit var spinner: Spinner
    lateinit var camaras: List<Camaras>

    fun loadSpinnerData() {
        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)

        val call: Call<List<Usuarios>> = purApp.getUsuarios()
        call.enqueue(object : Callback<List<Usuarios>> {
            override fun onResponse(
                call: Call<List<Usuarios>>,
                response: Response<Usuarios>
            ) {
                Log.d("TAG", response.code().toString() + "")
                var displayResponse = ""



                //responseText.setText(displayResponse)
            }

            override fun onFailure(
                call: Call<Usuarios?>,
                t: Throwable
            ) {
                call.cancel()
            }
        })




    }





}

