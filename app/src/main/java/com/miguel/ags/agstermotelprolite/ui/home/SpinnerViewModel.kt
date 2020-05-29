package com.miguel.ags.agstermotelprolite.ui.home

import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


class SpinnerViewModel : ViewModel() {
    lateinit var spinner: Spinner

    lateinit var camaras: List<Camaras>

    fun loadSpinnerData() {
        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)

        val releaseResponse = purApp.getCamarasById(0).execute()


    }





}

