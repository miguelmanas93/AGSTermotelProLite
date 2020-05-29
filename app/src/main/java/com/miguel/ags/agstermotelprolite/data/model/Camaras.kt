package com.miguel.ags.agstermotelprolite.data.model

data class Camaras(
    val idCamara: String,
    val nombre: String,
    val sondas: List<Sondas>
)
