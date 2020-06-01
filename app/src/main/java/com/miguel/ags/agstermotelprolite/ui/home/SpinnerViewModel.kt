package com.miguel.ags.agstermotelprolite.ui.home

import android.view.View
import android.widget.*
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.R
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.squareup.moshi.Moshi


class SpinnerViewModel : ViewModel(), AdapterView.OnItemSelectedListener {

    val mSpinnerData = MutableLiveData<List<String>>()
    val spiner: LiveData<List<String>> = mSpinnerData

    lateinit var usuariosRes: List<Usuarios>


    fun iniciarSpinner() {

        val values = arrayOf(
            "Pita",
            "Albacete",
            "Almeria",
            "Madrid"
        )

    }

    fun cargarDatos(){
        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)

        val usuariosResponse = purApp.getUsuarios().execute()
        val usuariosRes = usuariosResponse.body()
        Moshi.Builder().add(usuariosRes).build()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }


}


