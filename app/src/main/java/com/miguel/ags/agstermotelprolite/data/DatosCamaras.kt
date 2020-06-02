package com.miguel.ags.agstermotelprolite.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.miguel.ags.agstermotelprolite.utils.Avisos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DatosCamaras : ViewModel(){

    private var mensajeEstado = MutableLiveData<Avisos<String>>()

    private val _data = MutableLiveData<List<Usuarios>>()
    val data : LiveData<List<Usuarios>>
        get() = _data

    private val _dataCamara = MutableLiveData<List<Camaras>>()
    val dataCamara : LiveData<List<Camaras>>
        get() = _dataCamara

    private val _dataSondas = MutableLiveData<ArrayList<Sondas>>()
    val dataSondas : LiveData<ArrayList<Sondas>>
        get() = _dataSondas


    fun cargarUsuarios() {
        //Instancia de retrofit
        val service = RetrofitClient.getRetrofitInstance().create(APIService::class.java)
        //LLamada al metodo que vamos a user
        val callUsuarios: Call<List<Usuarios>> = service.getUsuarios()

        callUsuarios.enqueue(object : Callback<List<Usuarios>> {
            override fun onResponse(call: Call<List<Usuarios>>, response: Response<List<Usuarios>>) {
                //response.body()
                _data.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Usuarios>>, throwable: Throwable) {
                mensajeEstado.value = Avisos("Error al cargar la lista de usuario")

            }
        })
    }

    fun cargarDatosSondas(idSonda: Int, nombre: String) {

        //Instancia de retrofit
        val service = RetrofitClient.getRetrofitInstance().create(APIService::class.java)
        //LLamada al metodo que vamos a user
        val callSondas: Call<ArrayList<Sondas>> = service.getSondas(idSonda, nombre)

        callSondas.enqueue(object : Callback<ArrayList<Sondas>> {
            override fun onResponse(call: Call<ArrayList<Sondas>>, response: Response<ArrayList<Sondas>>) {
                //response.body()
                //_dataSonda.postValue(response.body())
            }
            override fun onFailure(call: Call<ArrayList<Sondas>>, throwable: Throwable) {
                mensajeEstado.value = Avisos("Error al cargar la lista de usuario")

            }
        })
    }

    fun cargarDatosCamaras(id : Int, name : String, pass : String) {
        //Instancia de retrofit
        val service = RetrofitClient.getRetrofitInstance().create(APIService::class.java)
        //LLamada al metodo que vamos a user
        val callCamaras: Call<List<Camaras>> = service.getCamaras(id,name,pass)

        callCamaras.enqueue(object : Callback<List<Camaras>> {
            override fun onResponse(call: Call<List<Camaras>>, response: Response<List<Camaras>>) {
                //response.body()
                _dataCamara.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Camaras>>, throwable: Throwable) {
                mensajeEstado.value = Avisos("Error al cargar la lista de camaras")

            }
        })
    }
}




