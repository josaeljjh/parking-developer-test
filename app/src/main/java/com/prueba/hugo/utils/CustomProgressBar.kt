package com.prueba.hugo.utils

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ProgressBar


class CustomProgressBar : ProgressBar {
    constructor(paramContext: Context?) : super(paramContext)
    constructor(
        paramContext: Context?,
        paramAttributeSet: AttributeSet?
    ) : super(paramContext, paramAttributeSet)

    constructor(
        paramContext: Context?,
        paramAttributeSet: AttributeSet?, paramInt: Int
    ) : super(paramContext, paramAttributeSet, paramInt)

    override fun draw(paramCanvas: Canvas) {
        val i = measuredWidth
        val j = measuredHeight
        paramCanvas.save()
        paramCanvas.rotate(135.0f, i / 2f, j / 2f)
        super.draw(paramCanvas)
        paramCanvas.restore()
    }
}