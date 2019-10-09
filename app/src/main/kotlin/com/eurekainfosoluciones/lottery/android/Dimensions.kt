package com.eurekainfosoluciones.lottery.android

import android.content.Context
import android.util.TypedValue

fun dpToPixels(context: Context, dps: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dps.toFloat(),
    context.resources.displayMetrics
)