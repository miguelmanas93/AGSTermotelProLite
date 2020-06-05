package com.miguel.ags.agstermotelprolite.repository

import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import com.miguel.ags.agstermotelprolite.network.APIService
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call

class ResChamberRepository : ChamberRepository, KoinComponent{

    private val apiService: APIService by inject()

    override fun getSondas(idSonda: Int, nombre: String) : Call<ArrayList<Sondas>> {
        return apiService.getSondas(idSonda,nombre)
    }

    override fun getCamaras() : Call<List<Camaras>> {
        return apiService.getCamaras()
    }


}