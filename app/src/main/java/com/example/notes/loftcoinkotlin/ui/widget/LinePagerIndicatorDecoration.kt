package com.example.notes.loftcoinkotlin.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R

@SuppressLint("ResourceAsColor")
class LinePagerIndicatorDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val activePaint: Paint = Paint()
    private val inactivePaint: Paint = Paint()
    private var indicatorRadius: Float = 0f

    init {
        val dm = context.resources.displayMetrics
        indicatorRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, dm)
        with(activePaint) {
            color = Color.WHITE
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        with(inactivePaint) {
            color = 0x44ffffff
            style = Paint.Style.FILL
            isAntiAlias = true
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter
        if (adapter != null) {
            val totalWidth = 2f * indicatorRadius * adapter.itemCount
            val posX = (parent.width - totalWidth) / 2f
            val posY = parent.height - indicatorRadius
            val lm = parent.layoutManager
            var currentIndicator = RecyclerView.NO_POSITION
            if (lm is LinearLayoutManager) {
                currentIndicator = lm.findFirstCompletelyVisibleItemPosition()
            }
            for (i in 0 until adapter.itemCount) {
                drawIndicator(c, posX + 4 * indicatorRadius * i, posY, currentIndicator == i)
            }
        }
    }

    private fun drawIndicator(canvas: Canvas, x: Float, y: Float, active: Boolean) {
        canvas.drawCircle(x, y, indicatorRadius, if (active) activePaint else inactivePaint)
    }

}