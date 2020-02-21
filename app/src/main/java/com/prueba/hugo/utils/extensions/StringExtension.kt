package com.prueba.hugo.utils.extensions

import java.util.concurrent.TimeUnit


/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

fun formatMin(minutos:Int):String{
    val formato = "%02d:%02d"
    val horasReales = TimeUnit.MINUTES.toHours(minutos.toLong())
    val minutosReales = TimeUnit.MINUTES.toMinutes(minutos.toLong()) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutos.toLong()))
    return "${String.format(formato, horasReales, minutosReales)} min"
}