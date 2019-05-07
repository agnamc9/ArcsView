package io.github.agnamc.arcview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Arc(val startAngle: Float, val finalAngle: Float, val color: Int) {

    val rect = RectF()
    val radius = 200f

    var currentAngle = 5f

    fun setup(parentWidth: Int, parentHeight: Int) {
        val centerX = parentWidth / 2f
        val centerY = parentHeight / 2f

        rect.set(
            centerX - radius, centerY - radius,
            centerX + radius, centerY + radius
        )
    }

    fun onDraw(paint: Paint, canvas: Canvas) {
        paint.color = color
        canvas.drawArc(rect, -90f, currentAngle, false, paint)
    }

}