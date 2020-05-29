package com.miguel.ags.agstermotelprolite.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Camaras(
    @Json(name = "id" ) val idCamara: Int,
    @Json(name = "nombre" ) val nombre: String
)
