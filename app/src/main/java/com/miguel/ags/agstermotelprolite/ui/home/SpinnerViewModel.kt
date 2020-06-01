package com.miguel.ags.agstermotelprolite.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Usuarios
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.miguel.ags.agstermotelprolite.utils.Avisos
import org.koin.ext.getScopeName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SpinnerViewModel : ViewModel() {

    private var mensajeEstado = MutableLiveData<Avisos<String>>()

    fun cargarDatos(): ArrayList<String> {
        var camaras: ArrayList<String> = arrayListOf(
            "Miguel",
            "Albacete",
            "Almeria",
            "Madrid"
        )

        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)
        val call: Call<List<Usuarios>> = purApp.getUsuarios()

        val camaras3 : Call<List<Usuarios>> = purApp.getCamaras("Miguel","","" )

        call.enqueue(object : Callback<List<Usuarios>> {
            override fun onResponse(call: Call<List<Usuarios>>, response: Response<List<Usuarios>>) {
                camaras = loadDataList(response.body())


                //Llamamos a los datos de forma más eficiente desde usuario
                //De esta forma en la variable usuarios vamos a tener los valores bien insertados
                //Podemos comprobar con serializable que los nombres sean iguales a los otros (los de la api)
                //Con estos podemos evitar el loadDataList

                val usuarios : List<Usuarios>? = response.body()


                //Falta escribir bien los datos
            }

            override fun onFailure(call: Call<List<Usuarios>>, throwable: Throwable) {
                mensajeEstado.value = Avisos("Error al cargar la lista de usuario")

            }
        })
        return camaras
    }

    private fun loadDataList(body: List<Usuarios>?): ArrayList<String> {

        return body as ArrayList<String>

    }

}




