package com.prueba.hugo.utils.extensions

import java.text.NumberFormat
import java.util.*

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

fun Double.ConvertDollar(): String {
    // creating the locale for US dollars UU
    val US = Locale("en", "US")
    val form: NumberFormat = NumberFormat.getCurrencyInstance(US)
    return (form.format(this))
}