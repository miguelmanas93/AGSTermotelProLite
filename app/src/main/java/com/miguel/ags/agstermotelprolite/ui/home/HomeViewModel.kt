package com.miguel.ags.agstermotelprolite.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Selecciona la camara"
    }
    val text: LiveData<String> = _text
}