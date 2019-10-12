package com.eurekainfosoluciones.lottery.widgets

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.eurekainfosoluciones.lottery.R
import com.eurekainfosoluciones.lottery.android.dpToPixels

class ReaderOverlayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.camera_overaly)
        style = Paint.Style.FILL
    }
    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    private val scanningPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        strokeWidth = 4f
    }
    private val scanningAnimator = ValueAnimator().apply {
        interpolator = AccelerateDecelerateInterpolator()
        duration = 2000
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
    }
    private val drawable: VectorDrawableCompat = VectorDrawableCompat.create(
        resources,
        R.drawable.bg_scan_area,
        null
    ) as VectorDrawableCompat
    private val leftMargin: Float = dpToPixels(context, 32)
    private val scanningOffset: Float = dpToPixels(context, 12)
    private val thinLineOffset: Float = dpToPixels(context, 2)

    private var scanArea: Float = 0f
    private var scanAreaInt: Int = 0
    private var topMargin: Float = 0f
    private var windowInsetTop: Int = 0
    private var scanningPosition = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        scanArea = measuredWidth - 2 * leftMargin
        scanAreaInt = scanArea.toInt()
        val availableHeight = measuredHeight - scanArea - windowInsetTop
        topMargin = availableHeight / 2.0f

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        scanningAnimator.run {
            setIntValues(
                scanningOffset.toInt(),
                (drawable.intrinsicWidth - leftMargin - scanningOffset - thinLineOffset).toInt()
            )
            addUpdateListener { value ->
                scanningPosition = (value.animatedValue as Int).toFloat()
                invalidate()
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bgPaint.xfermode = null
        with(canvas) {
            drawPaint(bgPaint)
            translate(leftMargin, topMargin + windowInsetTop)
            drawRect(thinLineOffset,
                thinLineOffset,
                scanArea - thinLineOffset,
                scanArea - thinLineOffset,
                bgPaint.apply {
                    xfermode = porterDuffXfermode
                }
            )
            drawable.let {
                it.setBounds(0, 0, scanAreaInt, scanAreaInt)
                it.draw(this)
            }

            translate(scanningPosition, 0f)
            drawLine(
                scanningPosition,
                scanningOffset,
                scanningPosition,
                scanArea - scanningOffset,
                scanningPaint
            )
        }
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        windowInsetTop = insets.systemWindowInsetTop
        return super.onApplyWindowInsets(insets)
    }

}