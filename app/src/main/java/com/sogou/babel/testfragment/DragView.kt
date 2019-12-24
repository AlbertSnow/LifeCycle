package com.sogou.babel.testfragment

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DragView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var lastX: Int = 0
    private var lastY: Int = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - lastX
                val offsetY = y - lastX
                (parent as View).scrollBy(-offsetX, 0)
            }
        }
        return true
    }
}