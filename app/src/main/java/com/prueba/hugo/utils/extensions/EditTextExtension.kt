package com.prueba.hugo.utils.extensions

import android.text.InputFilter
import android.widget.EditText
import com.prueba.hugo.utils.DecimalDigitsInputFilter


/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

fun EditText.decimalDigits(beforeZero: Int, afterZero: Int) {
    this.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(beforeZero, afterZero))
}