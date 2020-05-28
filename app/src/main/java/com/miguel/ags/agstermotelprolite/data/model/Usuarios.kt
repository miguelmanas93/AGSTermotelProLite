package com.miguel.ags.agstermotelprolite.data.model

data class Usuarios (
    var idUser : Int,
    var name : String,
    var pass : String,
    var camaras : List<Camaras>

)