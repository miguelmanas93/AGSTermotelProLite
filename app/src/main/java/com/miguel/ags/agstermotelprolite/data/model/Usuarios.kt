package com.miguel.ags.agstermotelprolite.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Usuarios(
    @Json(name = "id") var id: Int,
    @Json(name = "name") var name: String,
    @Json(name = "pass") var pass: String,
    @Json(name = "camaras") var camaras: List<Camaras>?

)


