package com.example.leanbackpaging.presentation.view.custom

import android.content.Context
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import androidx.leanback.widget.ImageCardView


class CustomImageCardView : ImageCardView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    fun setBorderColor(color: Int) {
        val border = background.constantState!!.newDrawable().mutate()
        border.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        background = border
    }

    fun setBorderWidth(width: Int) {
        val background = background.mutate()
        if (background is InsetDrawable) {
            val drawable = background.drawable
            if (drawable is ShapeDrawable) {
                val shapeDrawable = drawable
                shapeDrawable.paint.style = Paint.Style.STROKE
                shapeDrawable.paint.strokeWidth = width.toFloat()
                invalidate()
            }
        }
    }

}