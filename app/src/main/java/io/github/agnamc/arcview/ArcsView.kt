package io.github.agnamc.arcview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ArcsView(context: Context, attributes: AttributeSet) : View(context, attributes) {

    val arcs = listOf<Arc>(
        Arc(8f, 300f, Color.parseColor("#FA8A26")),
        Arc(6f, 110f, Color.parseColor("#F5464E")),
        Arc(5f, 25f, Color.parseColor("#6498F4"))
    )

    val arcBackground = Arc(0f, 360f, Color.parseColor("#EBEDEB"))
        .apply {
            currentAngle = 360f
        }

    val paint = Paint().apply {
        strokeWidth = 30f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        arcBackground.setup(w, h)

        arcs.forEach {
            it.setup(w, h)
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        arcBackground.onDraw(paint, canvas)

        arcs.forEach {
            it.onDraw(paint, canvas)
        }

    }

    fun animateMe() {
        arcs.forEach { arc ->
            ValueAnimator.ofFloat(arc.startAngle, arc.finalAngle)
                .apply {
                    duration = 700
                    addUpdateListener {
                        arc.currentAngle = it.animatedValue as Float
                        invalidate()
                    }
                }.start()
        }
    }
}