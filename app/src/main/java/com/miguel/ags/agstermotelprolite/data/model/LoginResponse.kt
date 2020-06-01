package com.miguel.ags.agstermotelprolite.data.model

data class LoginResponse(val error: Boolean, val mensaje:String, val usuario: Usuarios<Any?>)
