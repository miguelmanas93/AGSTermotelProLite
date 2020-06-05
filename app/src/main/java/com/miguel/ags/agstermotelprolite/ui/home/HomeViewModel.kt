package com.miguel.ags.agstermotelprolite.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.repository.ChamberRepository
import com.miguel.ags.agstermotelprolite.repository.RestUserRepository
import com.miguel.ags.agstermotelprolite.utils.Avisos
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel(), KoinComponent {

    private val userRepository: RestUserRepository by inject()
    private val chamberRepository: ChamberRepository by inject()

    private var mensajeEstado = MutableLiveData<Avisos<String>>()

    private val _dataUser = MutableLiveData<List<Usuarios>>()
    val data: LiveData<List<Usuarios>>
        get() = _dataUser

    private val _dataCamara = MutableLiveData<List<Camaras>>()
    val dataCamara: LiveData<List<Camaras>>
        get() = _dataCamara

    private val _dataSondas = MutableLiveData<ArrayList<Sondas>>()
    val dataSondas: LiveData<ArrayList<Sondas>>
        get() = _dataSondas

    private val _text = MutableLiveData<String>().apply {
        value = "Selecciona la camara"
    }
    val text: LiveData<String> = _text

    fun cargarUsuarios() {
        userRepository.cargarDatosUsuarios().enqueue(object : Callback<List<Usuarios>> {
            override fun onResponse(
                call: Call<List<Usuarios>>,
                response: Response<List<Usuarios>>
            ) {
                _dataUser.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Usuarios>>, throwable: Throwable) {
                mensajeEstado.value = Avisos("Error al cargar la lista de camaras")
            }
        })
    }

    fun cargarDatosSondas(idSonda: Int, nombre: String) {
        chamberRepository.getSondas(idSonda, nombre).enqueue(object : Callback<ArrayList<Sondas>> {
            override fun onResponse(
                call: Call<ArrayList<Sondas>>,
                response: Response<ArrayList<Sondas>>
            ) {
                //response.body()
                _dataSondas.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Sondas>>, throwable: Throwable) {
                mensajeEstado.value = Avisos("Error al cargar la lista de sondas")

            }
        })
    }

    fun cargarDatosCamaras(id: Int, name: String, pass: String) {
        chamberRepository.getCamaras().enqueue(object : Callback<List<Camaras>> {
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