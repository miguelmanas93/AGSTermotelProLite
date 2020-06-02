package com.miguel.ags.agstermotelprolite.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sondas (
    @Json(name = "numSerie") val numSerie: String,
    @Json(name = "alias") val alias: String,
    @Json(name = "descripcion") val descripcion: String,
    @Json(name = "temperatura") val temperatura: Double
)