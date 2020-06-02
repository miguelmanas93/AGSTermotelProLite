package com.miguel.ags.agstermotelprolite.utils

open class Avisos<out T>(private val content: T) {

    /**
     * Devuelve el contenido, incluso si ha sido manipulado.
     */
    fun peekContenido(): T = content
}