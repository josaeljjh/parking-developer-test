package com.prueba.hugo.utils

import android.view.animation.Animation
import android.view.animation.Transformation


/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */
class ProgressBarAnimation (
    private val from: Float,
    private val to: Float,
    private val pb: CustomProgressBar
) :
    Animation() {
    override fun applyTransformation(
        paramFloat: Float,
        paramTransformation: Transformation?
    ) {
        super.applyTransformation(paramFloat, paramTransformation)
        val f = from + paramFloat * (to - from)
        pb.progress = f.toInt()
    }

}