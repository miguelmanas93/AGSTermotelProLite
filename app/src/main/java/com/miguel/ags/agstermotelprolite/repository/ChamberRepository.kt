package com.miguel.ags.agstermotelprolite.repository

import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import retrofit2.Call

interface ChamberRepository {

    fun getSondas(idSonda: Int, nombre: String) : Call<ArrayList<Sondas>>
    fun getCamaras() : Call<List<Camaras>>
}