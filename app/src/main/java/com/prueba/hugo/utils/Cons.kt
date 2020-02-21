package com.prueba.hugo.utils

import androidx.fragment.app.FragmentActivity

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */
object Cons {
    //var sheetDialog: BottomDialogFragment? = null
    var activity: FragmentActivity? = null

    const val OFICIAL = "Oficial"
    const val RESIDENTE = "Residente"
    const val NO_RESIDENTE = "No residente"

    const val TIME_OFICIAL = 0.00
    const val TIME_NO_RESIDENTE = 0.50
    const val TIME_RESIDENTE = 0.05
    const val nPLACA = "nPLACA"
}